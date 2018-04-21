/*
 * @author ydp
 */
package com.example.southwest.checkin.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

public class FlightForm
{
	@NotEmpty(message = "first name is required")
	private String firstName;
	@NotEmpty(message = "Last name is required")
	private String lastName;
	@NotEmpty(message = "Confirmation # is required")
	private String confirmationNumber;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime departureTime;
	@NotEmpty(message = "Departure airport is required")
	private String departureAirport;
	@NotEmpty(message = "Destination airport is required")
	private String destinationAirport;
	@Email(message = "Email is invalid")
	private String email;
	private String phoneNumber;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getConfirmationNumber()
	{
		return confirmationNumber;
	}

	public void setConfirmationNumber(final String confirmationNumber)
	{
		this.confirmationNumber = confirmationNumber;
	}

	public LocalDateTime getDepartureTime()
	{
		return departureTime;
	}

	public void setDepartureTime(final LocalDateTime departureTime)
	{
		this.departureTime = departureTime;
	}

	public String getDepartureAirport()
	{
		return departureAirport;
	}

	public void setDepartureAirport(final String departureAirport)
	{
		this.departureAirport = departureAirport;
	}

	public String getDestinationAirport()
	{
		return destinationAirport;
	}

	public void setDestinationAirport(final String destinationAirport)
	{
		this.destinationAirport = destinationAirport;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof FlightForm))
		{
			return false;
		}

		final FlightForm flight = (FlightForm) o;

		if (firstName != null ? !firstName.equals(flight.firstName) : flight.firstName != null)
		{
			return false;
		}
		if (lastName != null ? !lastName.equals(flight.lastName) : flight.lastName != null)
		{
			return false;
		}
		if (confirmationNumber != null ? !confirmationNumber.equals(flight.confirmationNumber) : flight.confirmationNumber != null)
		{
			return false;
		}
		if (departureTime != null ? !departureTime.equals(flight.departureTime) : flight.departureTime != null)
		{
			return false;
		}
		if (departureAirport != null ? !departureAirport.equals(flight.departureAirport) : flight.departureAirport != null)
		{
			return false;
		}
		return destinationAirport != null ? destinationAirport.equals(flight.destinationAirport) : flight.destinationAirport == null;
	}

	@Override
	public int hashCode()
	{
		int result = firstName != null ? firstName.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (confirmationNumber != null ? confirmationNumber.hashCode() : 0);
		result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
		result = 31 * result + (departureAirport != null ? departureAirport.hashCode() : 0);
		result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
		return result;
	}
}
