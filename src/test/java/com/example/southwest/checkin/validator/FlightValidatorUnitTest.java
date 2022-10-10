///*
// * @author ydp
// */
//
//package com.example.southwest.checkin.validator;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.validation.BindException;
//
//import com.example.southwest.checkin.dto.Airport;
//import com.example.southwest.checkin.model.Flight;
//
//class FlightValidatorUnitTest
//{
//	private static final String AMERICA_NEW_YORK = "America/New_York";
//	private final FlightValidator flightValidator = new FlightValidator();
//	private Flight flight;
//	private BindException errors;
//	@Mock
//	private Airport origin;
//	@Mock
//	private Airport destination;
//
//	@BeforeEach
//	void setUp()
//	{
//		MockitoAnnotations.initMocks(this);
//		flight = flight();
//		errors = new BindException(flight, "flight");
//		when(origin.getName()).thenReturn("origin");
//		when(destination.getName()).thenReturn("destination");
//	}
//
//	@Test
//	void supports()
//	{
//		assertThat(flightValidator.supports(Flight.class)).isTrue();
//		assertThat(flightValidator.supports(Object.class)).isFalse();
//	}
//
//	@ParameterizedTest
//	@MethodSource(value = "validDepartureTimes")
//	void validDepartureDateTime(final LocalDateTime dateTime)
//	{
//		flight.setDepartureTime(dateTime);
//
//		flightValidator.validate(flight, errors);
//		assertThat(errors.hasErrors()).isFalse();
//	}
//
//	@ParameterizedTest
//	@MethodSource(value = "invalidDepartureTimes")
//	void invalidDepartureDateTime(final LocalDateTime dateTime)
//	{
//		flight.setDepartureTime(dateTime);
//
//		flightValidator.validate(flight, errors);
//		assertThat(errors.hasErrors()).isTrue();
//		assertThat(errors.getFieldErrorCount()).isEqualTo(1);
//		assertThat(errors.getFieldError("departureTime").getCode()).isEqualTo("invalid.date");
//	}
//
//	@Test
//	void validAirports()
//	{
//		flightValidator.validate(flight, errors);
//		assertThat(errors.hasErrors()).isFalse();
//	}
//
//	@Test
//	void invalidAirports()
//	{
//		when(origin.getName()).thenReturn(null);
//		when(destination.getName()).thenReturn(null);
//
//		flightValidator.validate(flight, errors);
//		assertThat(errors.hasErrors()).isTrue();
//		assertThat(errors.getFieldErrorCount()).isEqualTo(2);
//		assertThat(errors.getFieldError("departureAirport").getCode()).isEqualTo("invalid.departure.airport");
//		assertThat(errors.getFieldError("destinationAirport").getCode()).isEqualTo("invalid.destination.airport");
//	}
//
//	private static Stream<LocalDateTime> validDepartureTimes()
//	{
//		return Stream.of(
//				now().plusSeconds(1),
//				now().plusDays(1),
//				now().plusMonths(2));
//	}
//
//	private static Stream<LocalDateTime> invalidDepartureTimes()
//	{
//		return Stream.of(
//				null,
//				now().minusSeconds(3),
//				now().minusDays(1),
//				now().minusMonths(5));
//	}
//
//	private static LocalDateTime now()
//	{
//		return LocalDateTime.now(ZoneId.of(AMERICA_NEW_YORK));
//	}
//
//	private Flight flight()
//	{
//		final Flight flight = new Flight();
//		flight.setDepartureTime(now().plusDays(1));
//		flight.setConfirmationNumber("123ABC");
//		flight.setDepartureAirport(origin);
//		flight.setDestinationAirport(destination);
//		flight.setFirstName("John");
//		flight.setLastName("Doe");
//		flight.setTimezone(AMERICA_NEW_YORK);
//		return flight;
//	}
//}