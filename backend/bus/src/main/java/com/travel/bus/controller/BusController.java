package com.travel.bus.controller;

import com.travel.bus.dto.SeatDto;
import com.travel.bus.enums.AvailabilityStatus;
import com.travel.bus.exceptions.AlreadyExistsException;
import com.travel.bus.exceptions.NoDataFoundException;
import com.travel.bus.model.Bus;
import com.travel.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusController {

    @Autowired
    private BusService service;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Bus add(@RequestBody Bus bus) throws AlreadyExistsException {
        return service.add(bus);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Bus update(@RequestBody Bus bus) throws NoDataFoundException {
        return service.update(bus);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Bus> get() throws NoDataFoundException {
        return service.get();
    }

    @GetMapping("/{registrationNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Bus getById(@PathVariable("registrationNumber") String registrationNumber) throws NoDataFoundException {
        return service.getById(registrationNumber);
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Bus> getByAvailability() throws NoDataFoundException {
        return service.getByAvailability(AvailabilityStatus.AVAILABLE);
    }

    @DeleteMapping("/{registrationNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("registrationNumber") String registrationNumber) throws NoDataFoundException {
        service.delete(registrationNumber);
    }

    @PutMapping("/occupy")
    @ResponseStatus(HttpStatus.OK)
    public double occupySeat(@RequestBody SeatDto seatDto) throws NoDataFoundException {
        return service.occupySeat(seatDto.getRegistrationNumber(), seatDto.getSeatNumbers());
    }

    @PutMapping("/release")
    @ResponseStatus(HttpStatus.OK)
    public double releaseSeat(@RequestBody SeatDto seatDto) throws NoDataFoundException {
        return service.releaseSeat(seatDto.getRegistrationNumber(), seatDto.getSeatNumbers());
    }
}
