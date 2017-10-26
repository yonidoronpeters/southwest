/*
 * @author ydp
 */

package com.example.southwest.checkin.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.southwest.checkin.model.Airport;

public interface AirportService
{
	List<Airport> getAll();

	Airport getByCode();

	boolean isValidCode(final String code);

	boolean isValidDepartureTime(final String code, final LocalDateTime departureTime);
}
