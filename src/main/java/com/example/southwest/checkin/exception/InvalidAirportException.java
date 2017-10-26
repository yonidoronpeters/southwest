/*
 * @author ydp
 */
package com.example.southwest.checkin.exception;

public class InvalidAirportException extends RuntimeException
{
	public InvalidAirportException(final String msg)
	{
		super(msg);
	}
}
