/*
 * @author ydp
 */
package com.example.southwest.checkin.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJob implements Job
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException
	{
		LOGGER.info("*********** TEST JOB JUST RAN ***********");
	}           
}
