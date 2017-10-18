/*
 * @author ydp
 */

/*
 * @author ydp
 */
package com.example.southwest.checkin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.service.AirportCodeService;

@Service
public class DefaultAirportCodeService implements AirportCodeService
{
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
}
