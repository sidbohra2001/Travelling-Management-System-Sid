package com.travel.booking.model;

import com.travel.booking.enums.BusType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class TicketBus {
    @Id
    private String ticketId;
    private String bookingId;
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
}
