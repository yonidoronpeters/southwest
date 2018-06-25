/*
 * @author ydp
 */
package com.example.southwest.checkin.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.southwest.checkin.model.Flight;

public class TestJob implements Job
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException
	{
		final Object flight = jobExecutionContext.getMergedJobDataMap().get("flight");

		final Flight f = (Flight) flight;
		LOGGER.info("*********** TEST JOB JUST RAN FOR FLIGHT ***********\n{}", f);
	}
}
