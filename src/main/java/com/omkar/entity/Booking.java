package com.omkar.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String bookingReference;

	@Column(nullable = false)
	private String flightNumber;

	@Column(nullable = false)
	private String passengerName;

	@Column(nullable = false)
	private LocalDateTime bookedAt;

	@Column(nullable = false)
	private String status;

	public Booking() {
	}

	public Booking(String bookingReference, String flightNumber, String passengerName, LocalDateTime bookedAt,
			String status) {

		this.bookingReference = bookingReference;
		this.flightNumber = flightNumber;
		this.passengerName = passengerName;
		this.bookedAt = bookedAt;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}