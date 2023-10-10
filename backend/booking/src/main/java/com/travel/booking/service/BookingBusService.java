package com.travel.booking.service;

import com.travel.booking.exceptions.NoDataFoundException;
import com.travel.booking.model.BookingBus;
import com.travel.booking.model.TicketBus;

import java.util.List;
import java.util.Optional;

public interface BookingBusService {
    TicketBus bookTicket(BookingBus bookingBus);
    List<BookingBus> getBookings() throws NoDataFoundException;
    List<TicketBus> getTickets() throws NoDataFoundException;
    String deleteTicket(String ticketId) throws NoDataFoundException;
}
