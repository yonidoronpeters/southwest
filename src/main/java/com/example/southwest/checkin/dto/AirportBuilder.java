/*
 * @author ydp
 */

/*
 * @author ydp
 */
package com.example.southwest.checkin.dto;

public final class AirportBuilder
{
	private String code;
	private String name;
	private String city;
	private String country;
	private String timezone;
	private double latitude;
	private double longitude;

	private AirportBuilder()
	{
	}

	public static AirportBuilder airport()
	{
		return new AirportBuilder();
	}

	public AirportBuilder withCode(String code)
	{
		this.code = code;
		return this;
	}

	public AirportBuilder withName(String name)
	{
		this.name = name;
		return this;
	}

	public AirportBuilder withCity(String city)
	{
		this.city = city;
		return this;
	}

	public AirportBuilder withCountry(String country)
	{
		this.country = country;
		return this;
	}

	public AirportBuilder withTimezone(String timezone)
	{
		this.timezone = timezone;
		return this;
	}

	public AirportBuilder withLatitude(double latitude)
	{
		this.latitude = latitude;
		return this;
	}

	public AirportBuilder withLongitude(double longitude)
	{
		this.longitude = longitude;
		return this;
	}

	public Airport build()
	{
		final Airport airport = new Airport();
		airport.setCode(code);
		airport.setName(name);
		airport.setCity(city);
		airport.setCountry(country);
		airport.setLatitude(latitude);
		airport.setLongitude(longitude);
		return airport;
	}
}
