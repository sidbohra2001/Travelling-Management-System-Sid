package com.travel.booking.model;

import com.travel.booking.enums.Transport;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class BookingBus {
    @Id
    private String bookingId;
    @Enumerated(EnumType.STRING)
    private Transport transport;
    private String registrationNumber;
    private String sourceCity;
    private String destinationCity;
    private String startTime;
    private String endTime;
    private String pricePerSeat;
    @ElementCollection(targetClass = SeatMap.class, fetch = FetchType.EAGER)
    private List<SeatMap> seatMaps;
    private String totalPrice;
}


/*
* booking Id
* mode
* registrationNumber
* organizationName
* driverName
* driverPhoneNumber
* conductorName
* conductorPhoneNumber
* totalPrice
* map(seat, passenger)
* source
* destination
* startTime
* endTime
* duration
*
* */
