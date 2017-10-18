/*
 * @author ydp
 */

package com.example.southwest.checkin.service;

import java.util.List;

import com.example.southwest.checkin.model.Airport;

public interface AirportCodeService
{
	List<Airport> getAll();

	Airport getByCode();

	boolean isValidCode(final String code);
}
