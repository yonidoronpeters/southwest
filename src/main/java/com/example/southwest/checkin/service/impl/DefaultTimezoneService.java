/*
 * @author ydp
 */
package com.example.southwest.checkin.service.impl;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.southwest.checkin.dto.TimeZoneDTO;
import com.example.southwest.checkin.exception.InvalidTimeZoneException;
import com.example.southwest.checkin.service.TimezoneService;

@Service
public class DefaultTimezoneService implements TimezoneService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTimezoneService.class);

	@Value("${google.api.url}")
	private String url;
	@Value("${google.api.key}")
	private String apiKey;
	private final RestTemplate restTemplate;

	public DefaultTimezoneService(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public String getTimezone(final double latitude, final double longitude, final LocalDateTime dateTime)
	{
		if (dateTime == null)
		{
			return null;
		}
		final String queryUrl = url + "location=" + latitude + "," + longitude + "&key=" + apiKey + "&timestamp=" + dateTime.toEpochSecond(ZoneOffset.UTC);
		final TimeZoneDTO timeZoneDTO = restTemplate.getForObject(URI.create(queryUrl), TimeZoneDTO.class);
		if (timeZoneDTO == null || !"OK".equals(timeZoneDTO.getStatus()))
		{
			LOGGER.warn("Response code from google timezone api was {}", timeZoneDTO.getStatus());
			throw new InvalidTimeZoneException("Cannot get timezone for coordinates (" + latitude + "," + longitude + ") at date: " + dateTime);
		}
		LOGGER.info("Retrieved timezone: {} for coordinates ({}, {})", timeZoneDTO, latitude, longitude);
		return timeZoneDTO.getTimeZoneId();
	}
}
