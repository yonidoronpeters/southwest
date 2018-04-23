/*
 * @author ydp
 */

/*
 * @author ydp
 */
package com.example.southwest.checkin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.southwest.checkin.dto.Airport;

@Entity
@Table(indexes = {@Index(name = "Idx_confirmation",  columnList="confirmationNumber", unique = true)})
public class Flight
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String confirmationNumber;
	@Column(nullable = false)
	private LocalDateTime departureTime;
	@ManyToOne(targetEntity = Airport.class)
	private Airport departureAirport;
	@ManyToOne(targetEntity = Airport.class)
	private Airport destinationAirport;
	@Column(nullable = false)
	private String timezone;
	private String email;
	private String phoneNumber;

	public Flight() {}

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
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
