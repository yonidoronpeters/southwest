/*
 * @author ydp
 */
package com.example.southwest.checkin.validator;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.southwest.checkin.model.Flight;

@Component
public class FlightValidator implements Validator
{
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return Flight.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable final Object o, final Errors errors)
	{
		assert o != null;

		final Flight flight = (Flight) o;
		validateDepartureTime(errors, flight);
	}

	private void validateDepartureTime(final Errors errors, final Flight flight)
	{
		final ZoneId timezone = getTimezone(flight.getDepartureAirport());
		if (flight.getDepartureTime() == null
				|| flight.getDepartureTime().isBefore(LocalDateTime.now(timezone)))
		{
			errors.rejectValue("departureTime", "invalid_date");
		}
	}

	private ZoneId getTimezone(final String departureAirport)
	{
		// TODO need to implement
		return ZoneId.systemDefault();
	}
}
