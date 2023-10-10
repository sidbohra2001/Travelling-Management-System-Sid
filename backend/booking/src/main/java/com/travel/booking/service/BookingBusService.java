package com.travel.booking.service;

import com.travel.booking.model.BookingBus;
import com.travel.booking.model.TicketBus;

import java.util.List;
import java.util.Optional;

public interface BookingBusService {
    Optional<TicketBus> bookTicket(BookingBus bookingBus);
    Optional<List<BookingBus>> getBookings();
    Optional<List<TicketBus>> getTickets();
    String deleteTicket(String ticketId);
}
