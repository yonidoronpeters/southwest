/*
 * @author ydp
 */

package com.example.southwest.checkin.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.southwest.checkin.dto.Airport;
import com.example.southwest.checkin.exception.InvalidAirportException;
import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.AirportCodeService;
import com.example.southwest.checkin.service.FlightValidationService;
import com.example.southwest.checkin.service.TimezoneService;

@Service
public class DefaultFlightValidationService implements FlightValidationService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFlightValidationService.class);
	private final AirportCodeService airportCodeService;
	private final TimezoneService timezoneService;

	public DefaultFlightValidationService(final AirportCodeService airportCodeService, final TimezoneService timezoneService)
	{
		this.airportCodeService = airportCodeService;
		this.timezoneService = timezoneService;
	}

	@Override
	public boolean isValidCode(final String code)
	{
		return getByCode(code).isValid();
	}

	@Override
	public boolean isValidDepartureTime(final Flight flight)
	{
		final Airport airport = getByCode(flight.getDepartureAirport());

		return airport.isValid() && isValidFlightTime(airport, flight);
	}

	private Airport getByCode(final String code)
	{
		try
		{
			return airportCodeService.getByCode(code);
		}
		catch (final InvalidAirportException e)
		{
			LOGGER.info("Airport with code [{}] does not exist.", code);
			return new Airport();
		}
	}

	private boolean isValidFlightTime(final Airport airport, final Flight flight)
	{
		return flight.getDepartureTime() != null && isFlightInTheFuture(airport, flight);
	}

	private boolean isFlightInTheFuture(final Airport airport, final Flight flight)
	{
		return LocalDateTime
				.now(ZoneId.of(timezoneService.getTimezone(airport.getLatitude(), airport.getLongitude(), flight.getDepartureTime())))
				.isBefore(flight.getDepartureTime());
	}
}
