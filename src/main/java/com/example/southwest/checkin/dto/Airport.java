/*
 * @author ydp
 */

/*
 * @author ydp
 */
package com.example.southwest.checkin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport
{
	@JsonProperty(value = "iata")
	private String code;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "location")
	private String city;
	@JsonProperty(value = "country")
	private String country;
	@JsonProperty(value = "latitude")
	private double latitude;
	@JsonProperty(value = "longitude")
	private double longitude;

	public String getCode()
	{
		return code;
	}

	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
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

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(final double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(final double longitude)
	{
		this.longitude = longitude;
	}

	public boolean isValid()
	{
		return city != null;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Airport))
		{
			return false;
		}

		final Airport airport = (Airport) o;

		return code != null ? code.equals(airport.code) : airport.code == null;
	}

	@Override
	public int hashCode()
	{
		return code != null ? code.hashCode() : 0;
	}

	@Override
	public String toString()
	{
		return "Airport{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
