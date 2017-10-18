/*
 * @author ydp
 */
package com.example.southwest.checkin.model;

public class Airport
{
	private String code;
	private String city;
	private String country;
	private String timezone;

	public String getCode()
	{
		return code;
	}

	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(final String city)
	{
		this.city = city;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(final String country)
	{
		this.country = country;
	}

	public String getTimezone()
	{
		return timezone;
	}

	public void setTimezone(final String timezone)
	{
		this.timezone = timezone;
	}
}
