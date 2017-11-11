/*
 * @author ydp
 */

package com.example.southwest.checkin.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.southwest.checkin.exception.InvalidAirportException;
import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.AirportCodeService;
import com.example.southwest.checkin.service.AirportService;
import com.example.southwest.checkin.service.TimezoneService;

@Service
public class DefaultAirportService implements AirportService
{
	private static final Logger log = LoggerFactory.getLogger(DefaultAirportService.class);

	@Autowired
	private AirportCodeService airportCodeService;
	private TimezoneService timezoneService;

	@Override
	public Airport getByCode(final String code)
	{
		final Airport airport = airportCodeService.getByCode(code);
		airport.setTimezone(timezoneService.getTimezone(airport.getLatitude(), airport.getLongitude()));
		return airport;
	}

	@Override
	public boolean isValidCode(final String code)
	{
		try
		{
			getByCode(code);
		}
		catch (final InvalidAirportException e)
		{
			log.info("Airport with code [{}] does not exist.", code);
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidDepartureTime(final Flight flight)
	{
		final Airport airport = getByCode(flight.getDepartureAirport());
		return isFlightInTheFuture(airport, flight);
	}

	private boolean isFlightInTheFuture(final Airport airport, final Flight flight)
	{
		return LocalDateTime.now(ZoneId.of(airport.getTimezone())).isBefore(flight.getDepartureTime());
	}
}
