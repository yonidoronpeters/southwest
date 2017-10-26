/*
 * @author ydp
 */

package com.example.southwest.checkin.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.example.southwest.checkin.exception.InvalidAirportException;
import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.model.AirportBuilder;

class DefaultAirportCodeServiceUnitTest
{
	private static final String CODE = "COD";
	@InjectMocks
	private final DefaultAirportCodeService service = new DefaultAirportCodeService();
	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void airportCodeExists()
	{
		doReturn(airport()).when(restTemplate).getForObject(any(URI.class), eq(Airport.class));

		final Airport foundAirport = service.getByCode(CODE);
		assertThat(foundAirport)
				.isNotNull()
				.isEqualTo(airport());
	}

	@Test
	void invalidAirportCode()
	{
		doReturn(null).when(restTemplate).getForObject(any(URI.class), eq(Airport.class));

		assertThatThrownBy(() -> service.getByCode(CODE))
				.isExactlyInstanceOf(InvalidAirportException.class);
	}

	private Airport airport()
	{
		return AirportBuilder.airport()
				.withCode(CODE)
				.withName("Yellowstone Regional Airport")
				.withCity("Cody, WY")
				.withCountry("United States")
				.withLatitude(44.520194)
				.withLongitude(-109.023806)
				.build();
	}
}