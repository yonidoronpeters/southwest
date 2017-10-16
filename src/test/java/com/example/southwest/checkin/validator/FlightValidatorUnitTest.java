/*
 * @author ydp
 */

package com.example.southwest.checkin.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BindException;

import com.example.southwest.checkin.model.Flight;

class FlightValidatorUnitTest
{
	private final FlightValidator flightValidator = new FlightValidator();
	private final Flight flight = new Flight();
	private final BindException errors = new BindException(flight, "flight");

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
		assertThat(errors.getFieldError("departureTime").getCode()).isEqualTo("invalid_date");
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
}