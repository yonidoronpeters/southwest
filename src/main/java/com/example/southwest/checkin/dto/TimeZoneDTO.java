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
public class TimeZoneDTO
{
	@JsonProperty
	private String status;
	@JsonProperty
	private String timeZoneId;
	@JsonProperty
	private String timeZoneName;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(final String status)
	{
		this.status = status;
	}

	public String getTimeZoneId()
	{
		return timeZoneId;
	}

	public void setTimeZoneId(final String timeZoneId)
	{
		this.timeZoneId = timeZoneId;
	}

	public String getTimeZoneName()
	{
		return timeZoneName;
	}

	public void setTimeZoneName(final String timeZoneName)
	{
		this.timeZoneName = timeZoneName;
	}

	@Override
	public String toString()
	{
		return "TimeZoneDTO{" +
				"status='" + status + '\'' +
				", timeZoneId='" + timeZoneId + '\'' +
				", timeZoneName='" + timeZoneName + '\'' +
				'}';
	}
}
