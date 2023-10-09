package com.travel.bus.service;

import com.travel.bus.enums.AvailabilityStatus;
import com.travel.bus.exceptions.AlreadyExistsException;
import com.travel.bus.exceptions.NoDataFoundException;
import com.travel.bus.model.Bus;

import java.util.List;

public interface BusService {
    Bus add(Bus bus) throws AlreadyExistsException;
    Bus update(Bus bus) throws NoDataFoundException;
    List<Bus> get() throws NoDataFoundException;
    Bus getById(String registrationNumber) throws NoDataFoundException;
    List<Bus> getByAvailability(AvailabilityStatus availabilityStatus) throws NoDataFoundException;
    void delete(String registrationNumber) throws NoDataFoundException;
    double occupySeat(String registrationNumber, List<Integer> seatNumbers) throws NoDataFoundException;
    double releaseSeat(String registrationNumber, List<Integer> seatNumbers) throws NoDataFoundException;
}
