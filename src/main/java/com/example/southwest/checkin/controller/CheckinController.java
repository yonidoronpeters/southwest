
/*
 * @author ydp
 */

/*
 * @author ydp
 */

package com.example.southwest.checkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.southwest.checkin.model.Flight;

@Controller
public class CheckinController
{
	@GetMapping("/flight")
	public String checkinForm(final Model model)
	{
		model.addAttribute("flight", new Flight());
		return "flight";
	}

	@PostMapping("/flight")
	public String flightSubmit(@ModelAttribute final Flight flight)
	{
		return "successful-result";
	}
}
