package com.travel.booking.controller;

import com.travel.booking.dto.BookingBusDto;
import com.travel.booking.exceptions.NoDataFoundException;
import com.travel.booking.model.BookingBus;
import com.travel.booking.model.TicketBus;
import com.travel.booking.service.BookingBusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingBusController {

    @Autowired
    private BookingBusService bookingBusService;

    @PostMapping("/bookBus")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketBus bookTicket(@RequestBody BookingBusDto bookingBusDto) {
        BookingBus bookingBus = new BookingBus();
        BeanUtils.copyProperties(bookingBusDto, bookingBus);
        return bookingBusService.bookTicket(bookingBus);
    }

    @GetMapping("/busBookings")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingBus> getBookings() throws NoDataFoundException {
        return bookingBusService.getBookings();
    }

    @GetMapping("/busTickets")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketBus> getTickets() throws NoDataFoundException {
        return bookingBusService.getTickets();
    }

    @DeleteMapping("/busTicket/{ticketId}")
    public String deleteTicket(@PathVariable("ticketId") String ticketId) throws NoDataFoundException {
        return bookingBusService.deleteTicket(ticketId);
    }
}
