/*
 * @author ydp
 */
package com.example.southwest.checkin.service.impl;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.time.LocalDateTime;
import java.util.TimeZone;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.southwest.checkin.job.TestJob;
import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.JobSchedulingService;

@Component
public class QuartzJobSchedulingService implements JobSchedulingService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobSchedulingService.class);

	@Override
	public void scheduleCheckinJob(final Flight flight) throws SchedulerException
	{
		final SchedulerFactory schedFact = new StdSchedulerFactory();
		final Scheduler sched = schedFact.getScheduler();
		sched.start();

		final JobDetail job = newJob(TestJob.class)
				.withIdentity("checkinJob", flight.getId().toString())
				.storeDurably()
				.build();

		final LocalDateTime time = flight.getDepartureTime().minusDays(1);
		final Trigger trigger = newTrigger()
				.startAt(DateBuilder
						.newDateInTimezone(TimeZone.getTimeZone(flight.getTimezone()))
						.inYear(time.getYear())
						.inMonthOnDay(time.getMonthValue(), time.getDayOfMonth())
						.atHourMinuteAndSecond(time.getHour(), time.getMinute(), time.getSecond())
						.build())
				.withIdentity("checkinTrigger", flight.getId().toString())
				.build();

		LOGGER.info("Scheduling job {} for {}", job.getKey(), flight);
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);
	}
}