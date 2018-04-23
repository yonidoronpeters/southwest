/*
 * @author ydp
 */
package com.example.southwest.checkin.model;

import java.time.LocalDateTime;

import com.example.southwest.checkin.dto.Airport;

public final class FlightBuilder
{
	private String firstName;
	private String lastName;
	private String confirmationNumber;
	private LocalDateTime departureTime;
	private Airport departureAirport;
	private Airport destinationAirport;
	private String timezone;

	private FlightBuilder()
	{
	}

	public static FlightBuilder flight()
	{
		return new FlightBuilder();
	}

	public FlightBuilder withFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	public FlightBuilder withLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}

	public FlightBuilder withConfirmationNumber(String confirmationNumber)
	{
		this.confirmationNumber = confirmationNumber;
		return this;
	}

	public FlightBuilder withDepartureTime(LocalDateTime departureTime)
	{
		this.departureTime = departureTime;
		return this;
	}

	public FlightBuilder withDepartureAirport(Airport departureAirport)
	{
		this.departureAirport = departureAirport;
		return this;
	}

	public FlightBuilder withDestinationAirport(Airport destinationAirport)
	{
		this.destinationAirport = destinationAirport;
		return this;
	}

	public FlightBuilder withOriginTimezone(String timezone)
	{
		this.timezone = timezone;
		return this;
	}

	public Flight build()
	{
		final Flight flight = new Flight();
		flight.setFirstName(firstName);
		flight.setLastName(lastName);
		flight.setConfirmationNumber(confirmationNumber);
		flight.setDepartureTime(departureTime);
		flight.setDepartureAirport(departureAirport);
		flight.setDestinationAirport(destinationAirport);
		flight.setTimezone(timezone);
		return flight;
	}
}
