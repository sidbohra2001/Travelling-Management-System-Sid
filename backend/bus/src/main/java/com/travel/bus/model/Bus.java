package com.travel.bus.model;

import com.travel.bus.enums.AvailabilityStatus;
import com.travel.bus.enums.BusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bus {
    @Id
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    private String pricePerSeat;
    private String organizationName;
    private String driverName;
    private String conductorName;
    private String driverPhoneNumber;
    private String conductorPhoneNumber;
    private String sourceCity;
    private String startTime;
    private String destinationCity;
    private String endTime;
    private String duration;
    private int maxSeats;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> occupiedSeats;
}
