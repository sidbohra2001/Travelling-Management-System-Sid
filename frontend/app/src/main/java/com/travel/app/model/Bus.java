package com.travel.app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bus {
    private String registrationNumber;
    private String busType;
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
    private String availabilityStatus;
    private List<Integer> occupiedSeats;
}
