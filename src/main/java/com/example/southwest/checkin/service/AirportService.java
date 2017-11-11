/*
 * @author ydp
 */

package com.example.southwest.checkin.service;

import com.example.southwest.checkin.model.Airport;
import com.example.southwest.checkin.model.Flight;

/**
 *
 */
public interface AirportService
{
	Airport getByCode(String code);

	boolean isValidCode(String code);

	boolean isValidDepartureTime(Flight flight);
}
