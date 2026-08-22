package com.omkar.tools;

import java.time.LocalDate;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.omkar.entity.Booking;
import com.omkar.entity.Flight;
import com.omkar.service.BookingService;
import com.omkar.service.FlightService;

@Component
public class AiTools {

	private final FlightService flightService;

	private final BookingService bookingService;

	public AiTools(FlightService flightService, BookingService bookingService) {
		this.flightService = flightService;
		this.bookingService = bookingService;
	}

	@Tool(name = "searchFlights", description = "Search available flights between a source city and destination city for a specific travel date.")
	public List<Flight> searchFlights(@ToolParam(description = "Departure city, for example Mumbai") String source,

			@ToolParam(description = "Destination city, for example Delhi") String destination,

			@ToolParam(description = "Travel date in YYYY-MM-DD format") String travelDate) {

//		LocalDate date = LocalDate.parse(travelDate);
		LocalDate date;

		if ("today".equalsIgnoreCase(travelDate)) {

			date = LocalDate.now();

		} else if ("tomorrow".equalsIgnoreCase(travelDate)) {

			date = LocalDate.now().plusDays(1);

		} else {

			date = LocalDate.parse(travelDate);
		}

		return flightService.searchFlights(source, destination, date);
	}

	@Tool(name = "bookFlight", description = "Book one available flight for a passenger. Use this only when the user clearly asks to book a specific flight.")
	public Booking bookFlight(

			@ToolParam(description = "Flight number, for example FL101") String flightNumber,

			@ToolParam(description = "Passenger's full name") String passengerName) {

		return bookingService.bookFlight(flightNumber, passengerName);
	}

	@Tool(name = "getFlightDetails", description = """
			Get complete details of one specific flight using its flight number.
			Use this when the user asks for details about a particular flight.
			""")
	public Flight getFlightDetails(

			@ToolParam(description = "Flight number, for example FL101") String flightNumber) {

		System.out.println(">>> getFlightDetails TOOL EXECUTED: " + flightNumber);

		return flightService.getFlightDetails(flightNumber);
	}

	@Tool(name = "cancelBooking", description = """
			Cancel an existing flight booking using its booking reference.

			Use this tool ONLY when the user explicitly asks
			to cancel a booking.

			The booking reference must be an existing booking
			reference returned by the booking operation.

			Never invent a booking reference.
			""")
	public Booking cancelBooking(

			@ToolParam(description = "Booking reference, for example BK-A1B2C3D4") String bookingReference) {

		System.out.println(">>> cancelBooking TOOL EXECUTED: " + bookingReference);

		return bookingService.cancelBooking(bookingReference);
	}

	@Tool(name = "getBookingDetails", description = """
			Get the details and current status of an existing
			flight booking using its booking reference.

			Use this when the user asks about an existing booking,
			such as its status, passenger, flight number, or booking time.

			Do not use this tool to book or cancel a booking.
			Never invent a booking reference.
			""")
	public Booking getBookingDetails(

			@ToolParam(description = "Booking reference, for example BK-A1B2C3D4") String bookingReference) {

		System.out.println(">>> getBookingDetails TOOL EXECUTED: " + bookingReference);

		return bookingService.getBookingDetails(bookingReference);
	}
}