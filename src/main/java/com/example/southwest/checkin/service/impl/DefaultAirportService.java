/*
 * @author ydp
 */

package com.example.southwest.checkin.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.service.AirportCodeService;
import com.example.southwest.checkin.service.AirportService;
import com.example.southwest.checkin.service.TimezoneService;

@Service
public class DefaultAirportService implements AirportService
{
	@Autowired
	private AirportCodeService airportCodeService;
	private TimezoneService timezoneService;

	@Override
	public List<Airport> getAll()
	{
		return null;
	}

	@Override
	public Airport getByCode()
	{
		return null;
	}

	@Override
	public boolean isValidCode(final String code)
	{
		return false;
	}

	@Override
	public boolean isValidDepartureTime(final String code, final LocalDateTime departureTime)
	{
		return false;
	}
}
