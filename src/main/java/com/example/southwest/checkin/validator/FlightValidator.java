/*
 * @author ydp
 */
package com.example.southwest.checkin.validator;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.southwest.checkin.dto.Airport;
import com.example.southwest.checkin.model.Flight;

@Component
public class FlightValidator implements Validator
{
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightValidator.class);

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
		validateAirportCodes(flight, errors);
		if (!errors.hasErrors())
		{
			validateDepartureTime(flight, errors);
		}
	}

	private void validateAirportCodes(final Flight flight, final Errors errors)
	{
		if (!isValidCode(flight.getDepartureAirport()))
		{
			LOGGER.warn("Invalid origin airport [{}]", flight.getDepartureAirport());
			errors.rejectValue("departureAirport", "invalid.departure.airport");
		}
		if (!isValidCode(flight.getDestinationAirport()))
		{
			LOGGER.warn("Invalid destination airport [{}]", flight.getDestinationAirport());
			errors.rejectValue("destinationAirport", "invalid.destination.airport");
		}
	}

	private boolean isValidCode(final Airport airport)
	{
		return airport.getName() != null;
	}

	private void validateDepartureTime(final Flight flight, final Errors errors)
	{
		if (flight.getDepartureTime() == null || !isFlightInTheFuture(flight))
		{
			LOGGER.warn("Departure time [{}] for flight {} is in the past or invalid.", flight.getDepartureTime(), flight);
			errors.rejectValue("departureTime", "invalid.date");
		}
	}

	private boolean isFlightInTheFuture(final Flight flight)
	{
		return LocalDateTime.now(ZoneId.of(flight.getTimezone())).isBefore(flight.getDepartureTime());
	}
}
