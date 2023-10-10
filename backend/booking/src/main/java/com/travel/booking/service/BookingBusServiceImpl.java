package com.travel.booking.service;

import com.travel.booking.dto.BusDto;
import com.travel.booking.dto.SeatDto;
import com.travel.booking.enums.Transport;
import com.travel.booking.exceptions.NoDataFoundException;
import com.travel.booking.model.BookingBus;
import com.travel.booking.model.SeatMap;
import com.travel.booking.model.TicketBus;
import com.travel.booking.repo.BookingBusRepo;
import com.travel.booking.repo.TicketBusRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookingBusServiceImpl implements BookingBusService {

    @Autowired
    private BookingBusRepo bookingBusRepo;
    @Autowired
    private TicketBusRepo ticketBusRepo;
    @Autowired
    private RestTemplate rest;

    /*
     * Books ticket and maintains a Booking Record and Generates a Ticket for the User.
     * returns : Details of the generated ticket to the User.
     * throws : RestClientResponseException if there is any issue at the Bus Service.
     * */
    @Override
    public TicketBus bookTicket(BookingBus bookingBus) throws RestClientResponseException {
        BusDto busDto = getBusDetails(bookingBus.getRegistrationNumber());
        BeanUtils.copyProperties(busDto, bookingBus);
        bookingBus.setBookingDate(LocalDateTime.now());
        bookingBus.setBookingId(createBookingId(bookingBus.getTransport(), bookingBus.getRegistrationNumber()));
        List<Integer> seats = bookingBus.getSeatMaps().stream().mapToInt(SeatMap::getSeatNumber).boxed().toList();
        SeatDto seatDto = SeatDto.builder().registrationNumber(bookingBus.getRegistrationNumber()).seatNumbers(seats).build();
        double totalPrice = Double.parseDouble(occupySeats(seatDto));
        bookingBus.setTotalPrice(String.valueOf(totalPrice * 1.18));
        bookingBusRepo.save(bookingBus);
        return generateTicket(busDto, bookingBus);
    }

    /*
     * Fetch all the bookings.
     * returns : List of all the bookings.
     * throws : NoDataFoundException if no booking is found in the database.
     * */
    @Override
    public List<BookingBus> getBookings() throws NoDataFoundException {
        List<BookingBus> bookingBuses = bookingBusRepo.findAll();
        if (bookingBuses.isEmpty()) throw new NoDataFoundException("No Booking(s) found");
        return bookingBuses;
    }

    /*
     * Fetch all the tickets.
     * returns : List of all the tickets.
     * throws : NoDataFoundException if no ticket is found in the database.
     * */
    @Override
    public List<TicketBus> getTickets() throws NoDataFoundException {
        List<TicketBus> ticketBuses = ticketBusRepo.findAll();
        if (ticketBuses.isEmpty()) throw new NoDataFoundException("No Booking(s) found");
        return ticketBuses;
    }

    /*
     * Delete a ticket using ticketId.
     * returns : Refund price as String.
     * throws : NoDataFoundException if no ticket is found in the database.
     * */
    @Override
    public String deleteTicket(String ticketId) throws NoDataFoundException, RestClientResponseException {
        TicketBus ticketBus = ticketBusRepo.findById(ticketId).orElseThrow(() -> new NoDataFoundException("No ticket found for id " + ticketId));
        List<SeatMap> seatMaps = ticketBus.getSeatMaps();
        List<Integer> seats = seatMaps.stream().mapToInt(SeatMap::getSeatNumber).boxed().toList();
        SeatDto seatDto = SeatDto.builder().registrationNumber(ticketBus.getRegistrationNumber()).seatNumbers(seats).build();
        double totalCost = Double.parseDouble(releaseSeats(seatDto));
        ticketBusRepo.deleteById(ticketId);
        return String.valueOf(totalCost * 1.18);
    }

    /*
     * Rest Template based call to Bus MicroService to release the occupied seats.
     * returns : Refund amount as String.
     * throws : RestClientResponseException if there is some issue on the Bus Service.
     * */
    private String releaseSeats(SeatDto seatDto) throws RestClientResponseException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("role", "ADMIN");
        HttpEntity<SeatDto> http = new HttpEntity<>(seatDto, headers);
        return rest.exchange("http://BUS/bus/release", HttpMethod.PUT, http, String.class).getBody();
    }

    /*
     * Method to generate custom BookingId.
     * */
    private String createBookingId(Transport transport, String registrationNumber) {
        return "BMT" + transport.name() + registrationNumber + (int) (Math.random() * 9999 - 1000);
    }

    /*
     * Method to generate custom TicketId.
     * */
    private String createTicketId(Transport transport, String registrationNumber) {
        return "TKT" + transport.name() + registrationNumber + (int) (Math.random() * 9999 - 1000);
    }

    /*
     * Rest Template based call to Bus MicroService to fetch details of a bus by its registration number.
     * returns : Details of the bus as BusDto object.
     * throws : RestClientResponseException if there is some issue on the Bus Service.
     * */
    private BusDto getBusDetails(String registrationNumber) throws RestClientResponseException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("role", "ADMIN");
        HttpEntity<String> http = new HttpEntity<>(headers);
        return rest.exchange("http://BUS/bus/" + registrationNumber, HttpMethod.GET, http, BusDto.class).getBody();
    }

    /*
     * Method to generate Ticket from the information in booking details and busDto.
     * returns : Ticket for a bus.
     * */
    private TicketBus generateTicket(BusDto busDto, BookingBus bookingBus) {
        TicketBus ticketBus = new TicketBus();
        BeanUtils.copyProperties(busDto, ticketBus);
        ticketBus.setTicketId(createTicketId(bookingBus.getTransport(), busDto.getRegistrationNumber()));
        ticketBus.setBookingDate(bookingBus.getBookingDate());
        ticketBus.setBookingId(bookingBus.getBookingId());
        ticketBus.setSeatMaps(bookingBus.getSeatMaps());
        return ticketBusRepo.save(ticketBus);
    }

    /*
     * Rest Template based call to Bus MicroService to occupy seats.
     * returns : Total amount as String.
     * throws : RestClientResponseException if there is some issue on the Bus Service.
     * */
    private String occupySeats(SeatDto seatDto) throws RestClientResponseException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("role", "ADMIN");
        HttpEntity<SeatDto> http = new HttpEntity<>(seatDto, headers);
        return rest.exchange("http://BUS/bus/occupy", HttpMethod.PUT, http, String.class).getBody();
    }
}
