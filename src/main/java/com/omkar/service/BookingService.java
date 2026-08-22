package com.omkar.service;

import com.omkar.entity.Booking;
import com.omkar.entity.Flight;
import com.omkar.repository.BookingRepository;
import com.omkar.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final FlightRepository flightRepository;

	public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository) {

		this.bookingRepository = bookingRepository;
		this.flightRepository = flightRepository;
	}

	@Transactional
	public Booking bookFlight(String flightNumber, String passengerName) {

		System.out.println(">>> BOOK FLIGHT TOOL EXECUTED: " + flightNumber + " | passenger = " + passengerName);

		Flight flight = flightRepository.findByFlightNumberIgnoreCase(flightNumber)
				.orElseThrow(() -> new IllegalArgumentException("Flight not found: " + flightNumber));

		if (flight.getAvailableSeats() <= 0) {
			throw new IllegalStateException("No seats available for flight " + flightNumber);
		}

		flight.setAvailableSeats(flight.getAvailableSeats() - 1);

		flightRepository.save(flight);

		String bookingReference = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

		Booking booking = new Booking(bookingReference, flight.getFlightNumber(), passengerName, LocalDateTime.now(),
				"CONFIRMED");

		return bookingRepository.save(booking);
	}

	@Transactional
	public Booking cancelBooking(String bookingReference) {

		Booking booking = bookingRepository.findByBookingReference(bookingReference)
				.orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingReference));

		if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
			throw new IllegalStateException("Booking is already cancelled: " + bookingReference);
		}

		Flight flight = flightRepository.findByFlightNumberIgnoreCase(booking.getFlightNumber())
				.orElseThrow(() -> new IllegalArgumentException("Flight not found: " + booking.getFlightNumber()));

		// Return the seat to the flight
		flight.setAvailableSeats(flight.getAvailableSeats() + 1);

		flightRepository.save(flight);

		// Update booking status
		booking.setStatus("CANCELLED");

		return bookingRepository.save(booking);
	}

	public Booking getBookingDetails(String bookingReference) {

		return bookingRepository.findByBookingReference(bookingReference)
				.orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingReference));
	}
}