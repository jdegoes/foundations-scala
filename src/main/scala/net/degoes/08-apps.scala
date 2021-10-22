package net.degoes

import zio._

import java.time.LocalDate

final case class Plane()

final case class Flight()

final case class Airport()

final case class Passenger()

final case class Booking()

final case class SearchError()

final case class BookingError()

/**
 * EXERCISE
 *
 * Create an API with data types for searching for flights and booking
 * flights, with confirmations.
 */
trait FlightAPI {
  def findFlights(
    origin: Airport,
    dest: Airport,
    departure: LocalDate,
    returnDate: Option[LocalDate]
  ): IO[SearchError, List[Flight]]

  def bookFlight(passenger: Passenger, flight: Flight): IO[BookingError, Booking]
}

final case class EmailFailure()

final case class Email()

/**
 * EXERCISE
 *
 * Create an API with data types for sending emails.
 */
trait EmailAPI {
  def sendEmail(email: Email): IO[EmailFailure, Unit]
}

/**
 * EXERCISE
 *
 * Create a command-line, menu-driven application which allows the user to
 * search for and book flights.
 */
object AppsGraduation extends ZIOAppDefault {
  def run =
    Console.printLine("The End!")
}
