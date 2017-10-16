/*
 * @author ydp
 */

/*
 * @author ydp
 */
package com.example.southwest.checkin.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class Flight
{
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String confirmationNumber;
	private LocalDateTime departureTime;
	@NotNull
	private String departureAirport;
	@NotNull
	private String destinationAirport;

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
}
