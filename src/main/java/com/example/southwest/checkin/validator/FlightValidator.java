/*
 * @author ydp
 */
package com.example.southwest.checkin.validator;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.AirportCodeService;

@Component
public class FlightValidator implements Validator
{
	private static final Logger log = LoggerFactory.getLogger(FlightValidator.class);
	@Autowired
	private AirportCodeService airportCodeService;

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
		validateDepartureTime(flight, errors);
		validateAirportCodes(flight, errors);
	}

	private void validateAirportCodes(final Flight flight, final Errors errors)
	{
		if (!airportCodeService.isValidCode(flight.getDepartureAirport()))
		{
			log.warn("Invalid origin airport {}", flight.getDepartureAirport());
			errors.rejectValue("departureAirport", "invalid.departure.airport");
		}
		if (!airportCodeService.isValidCode(flight.getDestinationAirport()))
		{
			log.warn("Invalid destination airport {}", flight.getDestinationAirport());
			errors.rejectValue("destinationAirport", "invalid.destination.airport");
		}
	}

	private void validateDepartureTime(final Flight flight, final Errors errors)
	{
		final ZoneId timezone = getTimezone(flight.getDepartureAirport());
		if (flight.getDepartureTime() == null
				|| flight.getDepartureTime().isBefore(LocalDateTime.now(timezone)))
		{
			log.warn("Invalid departure time {}", flight.getDepartureTime());
			errors.rejectValue("departureTime", "invalid.date");
		}
	}

	private ZoneId getTimezone(final String departureAirport)
	{
		// TODO need to implement
		return ZoneId.systemDefault();
	}
}
