/*
 * @author ydp
 */

package com.example.southwest.checkin.service;

import com.example.southwest.checkin.model.Flight;

/**
 * A service for validating flights
 */
public interface FlightValidationService
{
	/**
	 * Check if the given airport code is valid
	 * @param code the airport iata code
	 * @return true if valid, false otherwise
	 */
	boolean isValidCode(String code);

	/**
	 * Checks if the given flight has a valid departure time
	 * @param flight the flight to validate
	 * @return true if valid departure, false otherwise
	 */
	boolean isValidDepartureTime(Flight flight);
}
