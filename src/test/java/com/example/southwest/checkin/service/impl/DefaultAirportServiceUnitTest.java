/*
 * @author ydp
 */

package com.example.southwest.checkin.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.southwest.checkin.exception.InvalidAirportException;
import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.model.AirportBuilder;
import com.example.southwest.checkin.model.FlightBuilder;
import com.example.southwest.checkin.service.AirportCodeService;
import com.example.southwest.checkin.service.AirportService;
import com.example.southwest.checkin.service.TimezoneService;

class DefaultAirportServiceUnitTest
{
	private static final String CODE = "JFK";
	private static final String NY_TIMEZONE = "America/New_York";
	@InjectMocks
	private final AirportService service = new DefaultAirportService();
	@Mock
	private AirportCodeService airportCodeService;
	@Mock
	private TimezoneService timezoneService;
	private final Airport airport = airport();

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getByCodeWithValidAirportCode()
	{
		givenAirportExist();

		final Airport foundAirport = service.getByCode(CODE);

		verify(timezoneService).getTimezone(airport.getLatitude(), airport.getLongitude());
		assertThat(airport).isEqualTo(foundAirport);
	}

	@Test
	void getByCodeInvalidAirportCode()
	{
		givenAirportDoesNotExist();

		assertThatThrownBy(() -> service.getByCode("ZZZ"))
				.isInstanceOf(InvalidAirportException.class);
	}

	@Test
	void isValidCode()
	{
		givenAirportExist();

		final boolean isValid = service.isValidCode(CODE);

		assertThat(isValid).isTrue();
	}

	@Test
	void isInvalidCode()
	{
		givenAirportDoesNotExist();

		final boolean isValid = service.isValidCode("ZZZ");

		assertThat(isValid).isFalse();
	}

	@Test
	void isValidDepartureTime()
	{
		doReturn(NY_TIMEZONE).when(timezoneService).getTimezone(anyDouble(), anyDouble());
		givenAirportExist();

		final boolean isValid = service.isValidDepartureTime(flight()
				// departure time in the future
				.withDepartureTime(LocalDateTime.now(ZoneId.of(NY_TIMEZONE)).plusDays(3))
				.build());

		assertThat(isValid).isTrue();
	}

	@Test
	void isInvalidDepartureTime()
	{
		doReturn(NY_TIMEZONE).when(timezoneService).getTimezone(anyDouble(), anyDouble());
		givenAirportExist();

		final boolean isValid = service.isValidDepartureTime(flight()
				// departure time in the PAST
				.withDepartureTime(LocalDateTime.now(ZoneId.of(NY_TIMEZONE)).minusMinutes(1))
				.build());

		assertThat(isValid).isFalse();
	}

	private void givenAirportDoesNotExist()
	{
		doThrow(new InvalidAirportException("Expected")).when(airportCodeService).getByCode(anyString());
	}

	private void givenAirportExist()
	{
		doReturn(airport).when(airportCodeService).getByCode(CODE);
		doReturn(NY_TIMEZONE).when(timezoneService).getTimezone(anyDouble(), anyDouble());
	}

	private Airport airport()
	{
		return AirportBuilder.airport()
				.withCode(CODE)
				.withName("John F Kennedy")
				.withCity("New York")
				.withCountry("U.S.")
				.withLongitude(-73.778926)
				.withLatitude(40.639751)
				.build();
	}

	private FlightBuilder flight()
	{
		return FlightBuilder.flight()
				.withConfirmationNumber("AGB456")
				.withDepartureAirport("JFK")
				.withDestinationAirport("LAX")
				.withFirstName("John")
				.withLastName("Doe");
	}
}