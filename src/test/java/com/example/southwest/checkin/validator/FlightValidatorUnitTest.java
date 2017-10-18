/*
 * @author ydp
 */

package com.example.southwest.checkin.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindException;

import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.AirportCodeService;

class FlightValidatorUnitTest
{
	@InjectMocks
	private final FlightValidator flightValidator = new FlightValidator();
	@Mock
	private AirportCodeService airportCodeService;
	private Flight flight;
	private BindException errors;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
		doReturn(true).when(airportCodeService).isValidCode(anyString());
		flight = flight();
		errors = new BindException(flight, "flight");
	}

	@Test
	void supports()
	{
		assertThat(flightValidator.supports(Flight.class)).isTrue();
		assertThat(flightValidator.supports(Object.class)).isFalse();
	}

	@ParameterizedTest
	@MethodSource(value = "validDepartureTimes")
	void validDepartureDateTime(final LocalDateTime dateTime)
	{
		flight.setDepartureTime(dateTime);

		flightValidator.validate(flight, errors);
		assertThat(errors.hasErrors()).isFalse();
	}

	@ParameterizedTest
	@MethodSource(value = "invalidDepartureTimes")
	void invalidDepartureDateTime(final LocalDateTime dateTime)
	{
		flight.setDepartureTime(dateTime);

		flightValidator.validate(flight, errors);
		assertThat(errors.hasErrors()).isTrue();
		assertThat(errors.getFieldErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("departureTime").getCode()).isEqualTo("invalid.date");
	}

	@ParameterizedTest
	@ValueSource(strings = {"DEN", "SFO", "SJC"})
	void validAirports(final String airportCode)
	{
		doReturn(true).when(airportCodeService).isValidCode(airportCode);

		flightValidator.validate(flight, errors);
		assertThat(errors.hasErrors()).isFalse();
	}

	@ParameterizedTest
	@ValueSource(strings = {"jakldsj", "james", "Hello", "null"})
	void invalidAirports(final String airportCode)
	{
		flight.setDepartureAirport(airportCode);
		flight.setDestinationAirport(airportCode);
		doReturn(false).when(airportCodeService).isValidCode(airportCode);

		flightValidator.validate(flight, errors);
		assertThat(errors.hasErrors()).isTrue();
		assertThat(errors.getFieldErrorCount()).isEqualTo(2);
		assertThat(errors.getFieldError("departureAirport").getCode()).isEqualTo("invalid.departure.airport");
		assertThat(errors.getFieldError("destinationAirport").getCode()).isEqualTo("invalid.destination.airport");
	}

	private static Stream<LocalDateTime> validDepartureTimes()
	{
		return Stream.of(
				LocalDateTime.now().plusSeconds(1),
				LocalDateTime.now().plusDays(1),
				LocalDateTime.now().plusMonths(2));
	}

	private static Stream<LocalDateTime> invalidDepartureTimes()
	{
		return Stream.of(
				null,
				LocalDateTime.now().minusSeconds(3),
				LocalDateTime.now().minusDays(1),
				LocalDateTime.now().minusMonths(5));
	}

	private Flight flight()
	{
		final Flight flight = new Flight();
		flight.setDepartureTime(LocalDateTime.now().plusDays(1));
		flight.setConfirmationNumber("123ABC");
		flight.setDepartureAirport("SFO");
		flight.setDestinationAirport("JFK");
		flight.setFirstName("John");
		flight.setLastName("Doe");
		return flight;
	}
}