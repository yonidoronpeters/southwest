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
		int length = url.length() + apiKey.length() + 50;
		final String queryUrl = new StringBuilder(length).append(url).append("location=").append(latitude).append(",").append(longitude).append("&apiKey=").append(apiKey).append("&timestamp=").append(dateTime.toEpochSecond(ZoneOffset.UTC)).toString();
		final TimeZoneDTO timeZoneDTO = restTemplate.getForObject(URI.create(queryUrl), TimeZoneDTO.class);
//		if (timeZoneDTO == null || !"OK".equals(timeZoneDTO.getStatus()))
//		{
//			LOGGER.warn("Response code from google timezone api was {}", timeZoneDTO.getStatus());
//			throw new InvalidTimeZoneException("Cannot get timezone for coordinates (" + latitude + "," + longitude + ") at date: " + dateTime);
//		}
		LOGGER.info("Retrieved timezone: {} for coordinates ({}, {})", timeZoneDTO, latitude, longitude);
		return timeZoneDTO.getTimeZoneId();
	}
}
