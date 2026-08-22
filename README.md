
# ✈️ Flight Booking AI Agent

An AI-powered flight booking assistant built with Spring Boot,
Spring AI, and Ollama.

## Features

- Natural-language flight search
- AI tool calling
- Flight details lookup
- Flight booking
- Booking status lookup
- Flight cancellation
- Conversation memory
- H2 in-memory database
- Local Ollama LLM

## Architecture

User
 ↓
Spring Boot
 ↓
Spring AI ChatClient
 ↓
Ollama
 ↓
Tool Calling
 ↓
Flight / Booking Services
 ↓
H2

## Tools

- searchFlights()
- getFlightDetails()
- bookFlight()
- getBookingDetails()
- cancelBooking()

## Tech Stack

- Java 21
- Spring Boot
- Spring AI
- Ollama
- Maven
- Spring Data JPA
- H2
- REST API

## Running the project

### 1. Start Ollama

```bash
ollama serve
