/*
 * @author ydp
 */
package com.example.southwest.checkin.service.impl;

import com.example.southwest.checkin.dto.Airport;
import com.example.southwest.checkin.service.AirportCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DefaultAirportCodeService implements AirportCodeService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAirportCodeService.class);

	@Value("${southwest.flight.api.url}")
	private String url;

	@Override
	public Airport getByCode(final String code)
	{
		Assert.notNull(code, "code cannot be null");

		final Airport airport = WebClient.create()
				.get()
				.uri(url + code)
				.retrieve()
				.bodyToMono(Airport.class)
				.block();
		LOGGER.info("Retrieved airport: {}", airport);
		return airport;
	}
}
