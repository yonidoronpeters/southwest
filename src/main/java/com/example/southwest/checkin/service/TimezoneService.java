/*
 * @author ydp
 */
package com.example.southwest.checkin.service;

import java.time.LocalDateTime;

public interface TimezoneService
{
	String getTimezone(double latitude, double longitude, LocalDateTime dateTime);
}
