
/*
 * @author ydp
 */

/*
 * @author ydp
 */

package com.example.southwest.checkin.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.validator.FlightValidator;

@Controller
public class CheckinController
{
	private final FlightValidator flightValidator;

	public CheckinController(final FlightValidator flightValidator)
	{
		this.flightValidator = flightValidator;
	}

	@GetMapping("/flight")
	public String checkinForm(final Model model)
	{
		model.addAttribute("flight", new Flight());
		return "flight";
	}

	@PostMapping("/flight")
	public String flightSubmit(@Valid @ModelAttribute final Flight flight, final BindingResult result)
	{
		flightValidator.validate(flight, result);
		if (result.hasErrors())
		{
			return "flight";
		}
		return "successful-result";
	}
}
