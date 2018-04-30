
/*
 * @author ydp
 */

/*
 * @author ydp
 */

package com.example.southwest.checkin.controller;

import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.southwest.checkin.converter.FlightFormToModelConverter;
import com.example.southwest.checkin.dto.FlightForm;
import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.repository.FlightRepository;
import com.example.southwest.checkin.service.JobSchedulingService;
import com.example.southwest.checkin.validator.FlightValidator;

@Controller
public class CheckinController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckinController.class);
	private final FlightValidator flightValidator;
	private final FlightFormToModelConverter formToModelConverter;
	private final FlightRepository flightRepository;
	private final JobSchedulingService jobSchedulingService;

	public CheckinController(final FlightValidator flightValidator, final FlightFormToModelConverter converter, final FlightRepository repo, final JobSchedulingService jobScheduler)
	{
		this.flightValidator = flightValidator;
		this.formToModelConverter = converter;
		this.flightRepository = repo;
		jobSchedulingService = jobScheduler;
	}

	@GetMapping("/flight")
	public String checkinForm(final Model model)
	{
		model.addAttribute("flightForm", new FlightForm());
		return "flight";
	}

	@PostMapping("/flight")
	public String flightSubmit(@Valid @ModelAttribute final FlightForm flightForm, final BindingResult result)
	{
		Flight flight = formToModelConverter.convert(flightForm);
		flightValidator.validate(flight, result);
		if (result.hasErrors())
		{
			return "flight";
		}
		flightRepository.save(flight);
		scheduleCheckin(flight);
		return "successful-result";
	}

	private void scheduleCheckin(final Flight flight)
	{
		try
		{
			jobSchedulingService.scheduleCheckinJob(flight);
		}
		catch (final SchedulerException e)
		{
			LOGGER.error("Error while scheduling job for flight {}", flight, e);
		}
	}
}
