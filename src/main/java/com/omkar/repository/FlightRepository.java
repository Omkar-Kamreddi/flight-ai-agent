package com.omkar.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findBySourceIgnoreCaseAndDestinationIgnoreCaseAndTravelDate(String source, String destination,
			LocalDate travelDate);

	Optional<Flight> findByFlightNumberIgnoreCase(String flightNumber);
}