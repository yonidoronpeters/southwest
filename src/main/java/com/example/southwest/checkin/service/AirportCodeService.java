/*
 * @author ydp
 */
package com.example.southwest.checkin.service;

import com.example.southwest.checkin.model.Airport;

/**
 * A service to get the airport name, location, and coordinates.
 */
public interface AirportCodeService
{
	/**
	 * @param code the IANA code of the airport
	 * @return {@link Airport} with no timezone populated
	 */
	Airport getByCode(String code);
}
