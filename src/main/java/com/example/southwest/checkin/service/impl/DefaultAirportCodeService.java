/*
 * @author ydp
 */
package com.example.southwest.checkin.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.southwest.checkin.exception.InvalidAirportException;
import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.service.AirportCodeService;

@Component
public class DefaultAirportCodeService implements AirportCodeService
{
	private static final String url = "http://www.airport-data.com/api/ap_info.json?iata=";
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Airport getByCode(final String code)
	{
		Assert.notNull(code, "code cannot be null");

		final Airport airport = restTemplate.getForObject(URI.create(url + code), Airport.class);
		if (airport == null)
		{
			throw new InvalidAirportException("Airport code [" + code + "] is invalid.");
		}
		return airport;
	}
}
