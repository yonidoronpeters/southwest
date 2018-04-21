/*
 * @author ydp
 */
package com.example.southwest.checkin.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.southwest.checkin.dto.FlightForm;
import com.example.southwest.checkin.model.Flight;
import com.example.southwest.checkin.service.AirportCodeService;
import com.example.southwest.checkin.service.TimezoneService;

@Component
public class FlightFormToModelConverter implements Converter<FlightForm, Flight>
{
	private final AirportCodeService airportCodeService;
	private final TimezoneService timezoneService;

	public FlightFormToModelConverter(AirportCodeService codeService, TimezoneService timezoneService)
	{
		this.airportCodeService = codeService;
		this.timezoneService = timezoneService;
	}

	@Override
	public Flight convert(final FlightForm flightForm)
	{
//		Airport departure = airportCodeService.getByCode(flightForm.getDepartureAirport());
//		Airport destination = airportCodeService.getByCode(flightForm.getDestinationAirport());
//		return FlightBuilder.flight()
//				.withFirstName(flightForm.getFirstName())
//				.withLastName(flightForm.getLastName())
//				.withConfirmationNumber(flightForm.getConfirmationNumber())
//				.withDepartureTime(flightForm.getDepartureTime())
//				.withDepartureAirport(departure)
//				.withDestinationAirport(destination)
//				.withEmail(flightForm.getEmail())
//				.withPhoneNumber(flightForm.getPhoneNumber())
//				.withDepartureTimezone(timezoneService.getTimezone(
//						departure.getLatitude(),
//						departure.getLongitude(),
//						flightForm.getDepartureTime()))
//				.build;
		return null;
	}
}
