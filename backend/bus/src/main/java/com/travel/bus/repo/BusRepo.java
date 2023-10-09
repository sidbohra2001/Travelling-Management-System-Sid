package com.travel.bus.repo;

import com.travel.bus.enums.AvailabilityStatus;
import com.travel.bus.enums.BusType;
import com.travel.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepo extends JpaRepository<Bus, String> {
    Optional<List<Bus>> findByAvailabilityStatus(AvailabilityStatus availabilityStatus);
    Optional<List<Bus>> findByAvailabilityStatusAndBusType(AvailabilityStatus availabilityStatus, BusType busType);
    Optional<List<Bus>> findBySourceCityAndDestinationCity(String sourceCity, String destinationCity);
    Optional<List<Bus>> findBySourceCityAndDestinationCityAndBusType(String sourceCity, String destinationCity, BusType busType);
    Optional<List<Bus>> findBySourceCityAndDestinationCityAndAvailabilityStatus(String sourceCity, String destinationCity, AvailabilityStatus availabilityStatus);
    Optional<List<Bus>> findBySourceCityAndDestinationCityAndAvailabilityStatusAndBusType(String sourceCity, String destinationCity, AvailabilityStatus availabilityStatus, BusType busType);
    Optional<List<Bus>> findByStartTime(String startTime);
    Optional<List<Bus>> findByBusType(BusType busType);
    Optional<List<Bus>> findByBusTypeAndAvailabilityStatus(BusType busType, AvailabilityStatus availabilityStatus);
}
