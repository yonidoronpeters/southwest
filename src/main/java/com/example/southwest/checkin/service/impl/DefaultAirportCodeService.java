/*
 * @author ydp
 */
package com.example.southwest.checkin.service.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.southwest.checkin.dto.Airport;
import com.example.southwest.checkin.service.AirportCodeService;

@Service
public class DefaultAirportCodeService implements AirportCodeService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAirportCodeService.class);

	@Value("${southwest.flight.api.url}")
	private String url;
	private final RestTemplate restTemplate;

	public DefaultAirportCodeService(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public Airport getByCode(final String code)
	{
		Assert.notNull(code, "code cannot be null");

		final Airport airport = restTemplate.getForObject(URI.create(url + code), Airport.class);
		LOGGER.info("Retrieved airport: {}", airport);
		return airport;
	}
}
