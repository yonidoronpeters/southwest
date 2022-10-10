/*
 * @author ydp
 */
package com.example.southwest.checkin.job;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.southwest.checkin.model.Flight;
import com.google.common.base.Strings;

public class SeleniumCheckinJob implements Job
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumCheckinJob.class);
	private static final String CONFIRMATION_NUMBER = "confirmationNumber";
	private static final String FIRST_NAME = "passengerFirstName";
	private static final String LAST_NAME = "passengerLastName";
	private static final String UTF_8 = StandardCharsets.UTF_8.name();
	private static final String SUBMIT_BUTTON_CLASS = "form-mixin--submit-button";
	private WebDriver driver;
	private String checkinUrl;
	private int maxNumOfAttempts;

	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException
	{
		try
		{
			LOGGER.info("Running job for check-in: {}", jobExecutionContext.getJobDetail());
			setUpWebDriver(jobExecutionContext);
			setMaxNumOfAttempts(jobExecutionContext);
			setCheckinUrl(jobExecutionContext);
			final Flight flight = getFlight(jobExecutionContext);

			submitPassengerInformation(flight);
			acceptTermsAndConditions();
			LOGGER.info("Checked in successfully to flight {}", flight);
			sendBoardingPass(flight);
		}
		catch (final WebDriverException e)
		{
			LOGGER.error("Selenium threw exception.", e);
			throw new JobExecutionException(e);
		}

		catch (final RuntimeException e)
		{
			LOGGER.error("Exception occurred while executing job {}", jobExecutionContext.getJobDetail(), e);
			throw new JobExecutionException(e);
		}
		finally
		{
			driver.quit();
		}
	}

	private void sendBoardingPass(final Flight flight)
	{
		emailBoardingPass(flight);
		textBoardingPass(flight);
	}

	private void setUpWebDriver(final JobExecutionContext context)
	{
		System.setProperty("webdriver.chrome.driver", getWebDriverLocation(context));
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private void textBoardingPass(final Flight flight)
	{
		if (!Strings.isNullOrEmpty(flight.getPhoneNumber()))
		{
			clickTextMessageButton();
			driver.findElement(By.id("textBoardingPass")).sendKeys(flight.getPhoneNumber());
			driver.findElement(By.id(SUBMIT_BUTTON_CLASS)).click();
			if (driver.findElement(By.className("message_success")).isDisplayed())
			{
				LOGGER.info("Texted boarding pass successfully to phone number {}", flight.getPhoneNumber());
			}
		}
	}

	private void emailBoardingPass(final Flight flight)
	{
		if (!Strings.isNullOrEmpty(flight.getEmail()))
		{
			driver.findElement(By.className("boarding-pass-options--button-email")).click();
			driver.findElement(By.id("emailBoardingPass")).sendKeys(flight.getEmail());
			driver.findElement(By.id(SUBMIT_BUTTON_CLASS)).click();
			if (driver.findElement(By.className("message_success")).isDisplayed())
			{
				LOGGER.info("Emailed boarding pass successfully to email {}", flight.getEmail());
			}
		}
	}

	private void acceptTermsAndConditions()
	{
		driver.findElement(By.className("submit-button")).click();
	}

	private void clickTextMessageButton()
	{
		// overlay popup prevents button from being clicked directly
		final WebElement textButton = driver.findElement(By.className("boarding-pass-options--button-text"));
		final JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", textButton);
	}

	private void submitPassengerInformation(final Flight flight) throws JobExecutionException
	{
		driver.get(encodedUri(flight));
		LOGGER.info("Attempting to check-in for flight {}", flight);
		int attempts = 0;
		try
		{
			do
			{
				LOGGER.info("Attempt #{}", attempts + 1);
				driver.findElement(By.id(SUBMIT_BUTTON_CLASS)).click();
				attempts++;
				Thread.sleep(300);
			} while ((attempts < maxNumOfAttempts) && driver.findElement(By.className("retrieve-reservation-form-container--form-wrapper")).isDisplayed());
			if (attempts >= maxNumOfAttempts) {
				LOGGER.error("Exceeded max number ({}) of check-in attempts for {}", maxNumOfAttempts, flight);
				throw new JobExecutionException("Max number of attempts (" + maxNumOfAttempts + ") was exceeded. Check-in failed for flight with confirmation #" + flight.getConfirmationNumber());
			}
		}
		catch (final InterruptedException e)
		{
			LOGGER.error("Interrupted exception", e);
			throw new JobExecutionException(e);
		}
	}

	private String encodedUri(final Flight flight) throws JobExecutionException
	{
		try
		{
			return checkinUrlWithParameters(flight);
		}
		catch (final UnsupportedEncodingException e)
		{
			LOGGER.error("Problem encoding URI for {}", flight, e);
			throw new JobExecutionException(e);
		}
	}

	private Flight getFlight(final JobExecutionContext jobExecutionContext)
	{
		final Object o = jobExecutionContext.getMergedJobDataMap().get("flight");
		return (Flight) o;
	}

	private void setCheckinUrl(final JobExecutionContext context)
	{
		checkinUrl = (String) context.getMergedJobDataMap().get("url");
	}

	private void setMaxNumOfAttempts(final JobExecutionContext context)
	{
		 maxNumOfAttempts = (int) context.getMergedJobDataMap().get("maxAttempts");
	}

	private String getWebDriverLocation(final JobExecutionContext context)
	{
		return (String) context.getMergedJobDataMap().get("webDriverLocation");
	}

	private String checkinUrlWithParameters(final Flight flight) throws UnsupportedEncodingException
	{
		return checkinUrl +
				"?" +
				CONFIRMATION_NUMBER + "=" + URLEncoder.encode(flight.getConfirmationNumber(), UTF_8) +
				"&" +
				FIRST_NAME + "=" + URLEncoder.encode(flight.getFirstName(), UTF_8) +
				"&" +
				LAST_NAME + "=" + URLEncoder.encode(flight.getLastName(), UTF_8);
	}
}
