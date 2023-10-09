package com.travel.bus.service;

import com.travel.bus.enums.AvailabilityStatus;
import com.travel.bus.exceptions.AlreadyExistsException;
import com.travel.bus.exceptions.NoDataFoundException;
import com.travel.bus.model.Bus;
import com.travel.bus.repo.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.travel.bus.enums.AvailabilityStatus.*;

@Component
public class BusServiceImpl implements BusService{

    @Autowired private BusRepo repo;

    /*
    * Adds a new bus to the database.
    * Adds values like maxSeats, occupiedSeats, and sets duration based on start and end time.
    * returns : New Generated Bus Object.
    * */
    @Override
    public Bus add(Bus bus) throws AlreadyExistsException {
        if(repo.existsById(bus.getRegistrationNumber())) throw new AlreadyExistsException("Bus with Registration Number "+bus.getRegistrationNumber()+" already exists");
        bus.setMaxSeats(20);
        bus.setOccupiedSeats(new ArrayList<>());
        int startTime = Integer.parseInt(bus.getStartTime());
        int endTime = Integer.parseInt(bus.getEndTime());
        int diffTime = endTime-startTime;
        int duration = (startTime < endTime)?(diffTime):(2400-Math.abs(diffTime));
        bus.setDuration(String.valueOf(duration));
        bus.setAvailabilityStatus(AVAILABLE);
        return repo.save(bus);
    }

    /*
     * Updates an existing bus in the database.
     * Updates values like driver info, conductor info, source, destination, timings, duration, seatPrice.
     * returns : Updated Bus Object.
     * throws : NoDataFoundException if bus registrationNumber is not found in the database.
     * */
    @Override
    public Bus update(Bus bus) throws NoDataFoundException {
        Bus existingBus = repo.findById(bus.getRegistrationNumber()).orElseThrow(() -> new NoDataFoundException("No Bus exists for Registration Number "+bus.getRegistrationNumber()));
        existingBus.setStartTime(bus.getStartTime());
        existingBus.setEndTime(bus.getEndTime());
        existingBus.setConductorName(bus.getConductorName());
        existingBus.setDriverName(bus.getDriverName());
        existingBus.setConductorPhoneNumber(bus.getConductorPhoneNumber());
        existingBus.setDriverPhoneNumber(bus.getDriverPhoneNumber());
        existingBus.setPricePerSeat(bus.getPricePerSeat());
        int startTime = Integer.parseInt(existingBus.getStartTime());
        int endTime = Integer.parseInt(existingBus.getEndTime());
        int diffTime = endTime-startTime;
        int duration = (startTime < endTime)?(diffTime):(2400-Math.abs(diffTime));
        existingBus.setDuration(String.valueOf(duration));
        existingBus.setSourceCity(bus.getSourceCity());
        existingBus.setDestinationCity(bus.getDestinationCity());
        return repo.save(existingBus);
    }

    /*
     * Get all the existing buses in the database.
     * returns : List of all Buses.
     * throws : NoDataFoundException if bus registrationNumber is not found in the database.
     * */
    @Override
    public List<Bus> get() throws NoDataFoundException {
        List<Bus> buses = repo.findAll();
        if(buses.isEmpty()) throw new NoDataFoundException("No Buses exists in the Database !!!");
        return buses;
    }

    /*
     * Get existing bus in the database by registrationNumber.
     * returns : Details of requested Bus.
     * throws : NoDataFoundException if bus registrationNumber is not found in the database.
     * */
    @Override
    public Bus getById(String registrationNumber) throws NoDataFoundException {
        return repo.findById(registrationNumber).orElseThrow(() -> new NoDataFoundException("No Bus found for id "+registrationNumber));
    }

    /*
     * Get all the existing buses in the database which have seats available.
     * returns : List of all Buses where seats are available.
     * throws : NoDataFoundException if bus registrationNumber is not found in the database.
     * */
    @Override
    public List<Bus> getByAvailability(AvailabilityStatus availabilityStatus) throws NoDataFoundException {
        return repo.findByAvailabilityStatus(availabilityStatus).orElseThrow(() -> new NoDataFoundException("No "+availabilityStatus+" bus(es) found !!!"));
    }

    /*
     * Delete an existing buses in the database.
     * returns : Void.
     * throws : NoDataFoundException if bus registrationNumber is not found in the database.
     * */
    @Override
    public void delete(String registrationNumber) throws NoDataFoundException {
        if(!repo.existsById(registrationNumber)) throw new NoDataFoundException("No Bus exists for Registration Number "+registrationNumber);
        repo.deleteById(registrationNumber);
    }

    /*
    * Occupy available seats in a bus, on ticket booking.
    * returns : Total Price of booking all the tickets.
    * throws : NoDataFoundException if bus registrationNumber is not found in the database.
    * */
    @Override
    public double occupySeat(String registrationNumber, List<Integer> seatNumbers) throws NoDataFoundException {
        Bus bus = getById(registrationNumber);
        List<Integer> occupiedSeats = bus.getOccupiedSeats();
        occupiedSeats.addAll(seatNumbers);
        double pricePerSeat = Double.parseDouble(bus.getPricePerSeat());
        double totalPrice = pricePerSeat*seatNumbers.size();
        if(occupiedSeats.size() == bus.getMaxSeats()){
            bus.setAvailabilityStatus(FULL);
        }
        repo.save(bus);
        return totalPrice;
    }

    /*
     * Release occupied seats in a bus, on ticket cancellation.
     * returns : Total Price of refunding all the tickets.
     * throws : NoDataFoundException if bus registrationNumber is not found in the database.
     * */
    @Override
    public double releaseSeat(String registrationNumber, List<Integer> seatNumbers) throws NoDataFoundException {
        Bus bus = getById(registrationNumber);
        List<Integer> occupiedSeats = bus.getOccupiedSeats();
        occupiedSeats.removeAll(seatNumbers);
        double pricePerSeat = Double.parseDouble(bus.getPricePerSeat());
        double totalPrice = pricePerSeat*seatNumbers.size();
        if(occupiedSeats.size() < bus.getMaxSeats()){
            bus.setAvailabilityStatus(AVAILABLE);
        }
        repo.save(bus);
        return totalPrice;
    }
}
