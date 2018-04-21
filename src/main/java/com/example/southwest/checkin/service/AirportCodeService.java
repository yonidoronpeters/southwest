/*
 * @author ydp
 */
package com.example.southwest.checkin.service;

import com.example.southwest.checkin.dto.Airport;
import com.example.southwest.checkin.exception.InvalidAirportException;

/**
 * A service to get the airport name, location, and coordinates.
 */
public interface AirportCodeService
{
	/**
	 * @param code the IANA code of the airport
	 * @return {@link Airport} with no timezone populated
	 * @throws InvalidAirportException if code is invalid
	 */
	Airport getByCode(String code) throws InvalidAirportException;
}
