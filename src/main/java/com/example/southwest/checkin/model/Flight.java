/*
 * @author ydp
 */

/*
 * @author ydp
 */
package com.example.southwest.checkin.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.southwest.checkin.dto.Airport;

@Entity
public class Flight
{
	@Id
	private UUID id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String confirmationNumber;
	@Column
	private LocalDateTime departureTime;
	@Column
	private Airport departureAirport;
	@Column
	private Airport destinationAirport;
	@Column
	private String timezone;
	@Column
	private String email;
	@Column
	private String phoneNumber;

	public UUID getId()
	{
		return id;
	}

	public void setId(final UUID id)
	{
		this.id = id;
	}

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

	public Airport getDepartureAirport()
	{
		return departureAirport;
	}

	public void setDepartureAirport(final Airport departureAirport)
	{
		this.departureAirport = departureAirport;
	}

	public Airport getDestinationAirport()
	{
		return destinationAirport;
	}

	public void setDestinationAirport(final Airport destinationAirport)
	{
		this.destinationAirport = destinationAirport;
	}

	public String getTimezone()
	{
		return timezone;
	}

	public void setTimezone(final String timezone)
	{
		this.timezone = timezone;
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
		if (!(o instanceof Flight))
		{
			return false;
		}

		final Flight flight = (Flight) o;

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
