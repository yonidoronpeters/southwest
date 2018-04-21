/*
 * @author ydp
 */
package com.example.southwest.checkin.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.FlightValidationService;

@Component
public class FlightValidator implements Validator
{
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightValidator.class);
	private final FlightValidationService flightValidationService;

	public FlightValidator(final FlightValidationService flightValidationService)
	{
		this.flightValidationService = flightValidationService;
	}

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
		if (!flightValidationService.isValidCode(flight.getDepartureAirport()))
		{
			LOGGER.warn("Invalid origin airport [{}]", flight.getDepartureAirport());
			errors.rejectValue("departureAirport", "invalid.departure.airport");
		}
		if (!flightValidationService.isValidCode(flight.getDestinationAirport()))
		{
			LOGGER.warn("Invalid destination airport [{}]", flight.getDestinationAirport());
			errors.rejectValue("destinationAirport", "invalid.destination.airport");
		}
	}

	private void validateDepartureTime(final Flight flight, final Errors errors)
	{
		if (!flightValidationService.isValidDepartureTime(flight))
		{
			LOGGER.warn("Invalid departure time [{}] for origin airport [{}]", flight.getDepartureTime(), flight.getDepartureAirport());
			errors.rejectValue("departureTime", "invalid.date");
		}
	}
}
