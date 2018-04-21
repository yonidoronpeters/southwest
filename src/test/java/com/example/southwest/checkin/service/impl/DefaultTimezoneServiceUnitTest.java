/*
 * @author ydp
 */

package com.example.southwest.checkin.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.example.southwest.checkin.dto.TimeZoneDTO;
import com.example.southwest.checkin.exception.InvalidTimeZoneException;

class DefaultTimezoneServiceUnitTest
{
	private static final double LATITUDE = 44.520194;
	private static final double LONGITUDE = -109.023806;
	private static final String DENVER = "America/Denver";

	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private DefaultTimezoneService timezoneService;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
		timezoneService = new DefaultTimezoneService(restTemplate);
	}

	@Test
	void getTimezoneIdSuccess()
	{
		doReturn(timeZoneDTO("OK")).when(restTemplate).getForObject(any(), any());

		final String timezone = timezoneService.getTimezone(LATITUDE, LONGITUDE, LocalDateTime.now(ZoneId.of(DENVER)));

		assertThat(timezone).isEqualTo(DENVER);
	}

	@Test
	void getTimezoneIdFails_Null()
	{
		doReturn(new TimeZoneDTO()).when(restTemplate).getForObject(any(), any());

		assertThatThrownBy(() -> timezoneService.getTimezone(LATITUDE, LONGITUDE, LocalDateTime.now(ZoneId.of(DENVER))))
			.isInstanceOf(InvalidTimeZoneException.class);
	}

	@Test
	void getTimezoneIdFails_NotOkReturned()
	{
		doReturn(timeZoneDTO("failed")).when(restTemplate).getForObject(any(), any());

		assertThatThrownBy(() -> timezoneService.getTimezone(LATITUDE, LONGITUDE, LocalDateTime.now(ZoneId.of(DENVER))))
				.isInstanceOf(InvalidTimeZoneException.class);
	}

	private TimeZoneDTO timeZoneDTO(final String status)
	{
		final TimeZoneDTO timezone = new TimeZoneDTO();
		timezone.setStatus(status);
		timezone.setTimeZoneId(DENVER);
		timezone.setTimeZoneName("Mountain Standard Time");
		return timezone;
	}
}