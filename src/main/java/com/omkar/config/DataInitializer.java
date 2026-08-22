package com.omkar.config;

import com.omkar.entity.Flight;
import com.omkar.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner loadFlightData(FlightRepository flightRepository) {

		return args -> {

			LocalDate tomorrow = LocalDate.now().plusDays(1);

			flightRepository.save(new Flight("FL101", "Mumbai", "Delhi", tomorrow, LocalTime.of(6, 30),
					LocalTime.of(8, 45), BigDecimal.valueOf(5500), 20));

			flightRepository.save(new Flight("FL102", "Mumbai", "Delhi", tomorrow, LocalTime.of(10, 0),
					LocalTime.of(12, 15), BigDecimal.valueOf(7200), 15));

			flightRepository.save(new Flight("FL103", "Mumbai", "Delhi", tomorrow, LocalTime.of(18, 30),
					LocalTime.of(20, 45), BigDecimal.valueOf(6800), 10));

			flightRepository.save(new Flight("FL104", "Mumbai", "Delhi", tomorrow, LocalTime.of(21, 0),
					LocalTime.of(23, 15), BigDecimal.valueOf(6200), 8));

			System.out.println("Dummy flight data loaded successfully.");
		};
	}
}