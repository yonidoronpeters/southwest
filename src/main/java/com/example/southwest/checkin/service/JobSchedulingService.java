/*
 * @author ydp
 */
package com.example.southwest.checkin.service;

import org.quartz.SchedulerException;

import com.example.southwest.checkin.model.Flight;

public interface JobSchedulingService
{
	/**
	 * Create a job that will be triggered 24hrs before the flight departure time
	 * @param flight the flight to check-in to
	 */
	void scheduleCheckinJob(Flight flight) throws SchedulerException;
}
