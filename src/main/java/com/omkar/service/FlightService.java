package com.omkar.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.omkar.entity.Flight;
import com.omkar.repository.FlightRepository;

@Service
public class FlightService {

	private final FlightRepository flightRepository;

	public FlightService(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	public List<Flight> searchFlights(String source, String destination, LocalDate travelDate) {

		return flightRepository.findBySourceIgnoreCaseAndDestinationIgnoreCaseAndTravelDate(source, destination,
				travelDate);
	}

	public Flight getFlightDetails(String flightNumber) {

		return flightRepository.findByFlightNumberIgnoreCase(flightNumber)
				.orElseThrow(() -> new IllegalArgumentException("Flight not found: " + flightNumber));
	}
}