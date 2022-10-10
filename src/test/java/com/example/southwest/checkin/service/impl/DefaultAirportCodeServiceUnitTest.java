///*
// * @author ydp
// */
//
//package com.example.southwest.checkin.service.impl;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doReturn;
//
//import java.net.URI;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.client.RestTemplate;
//
//import com.example.southwest.checkin.dto.Airport;
//import com.example.southwest.checkin.dto.AirportBuilder;
//
//class DefaultAirportCodeServiceUnitTest
//{
//	private static final String CODE = "COD";
//	@Mock
//	private RestTemplate restTemplate;
//	@InjectMocks
//	private DefaultAirportCodeService service;
//
//	@BeforeEach
//	void setUp()
//	{
//		MockitoAnnotations.initMocks(this);
//		service = new DefaultAirportCodeService(restTemplate);
//	}
//
//	@Test
//	void airportCodeExists()
//	{
//		doReturn(airport()).when(restTemplate).getForObject(any(URI.class), eq(Airport.class));
//
//		final Airport foundAirport = service.getByCode(CODE);
//		assertThat(foundAirport)
//				.isNotNull()
//				.isEqualTo(airport());
//	}
//
//	@Test
//	void invalidAirport()
//	{
//		doReturn(new Airport()).when(restTemplate).getForObject(any(URI.class), eq(Airport.class));
//
//		final Airport airport = service.getByCode(CODE);
//		assertThat(airport).isNotNull();
//		assertThat(airport.getName()).isNull();
//	}
//
//	private Airport airport()
//	{
//		return AirportBuilder.airport()
//				.withCode(CODE)
//				.withName("Yellowstone Regional Airport")
//				.withCity("Cody, WY")
//				.withCountry("United States")
//				.withLatitude(44.520194)
//				.withLongitude(-109.023806)
//				.build();
//	}
//}