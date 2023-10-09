package com.travel.bus.model;

import com.travel.bus.enums.AvailabilityStatus;
import com.travel.bus.enums.BusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[ ]?[A-Z]{2}[0-9]{4}$", message = "Invalid registration number format")
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    @Pattern(regexp = "^[0-9]{1,4}", message = "Invalid price per seat format")
    private String pricePerSeat;
    @Pattern(regexp = "^[A-Za-z ]{2,30}", message = "Invalid Driver's Name Format")
    private String driverName;
    @Pattern(regexp = "^[A-Za-z ]{2,30}", message = "Invalid Conductor's Name Format")
    private String conductorName;
    @Pattern(regexp = "^[+][0-9]{1,2}[0-9]{10}", message = "Invalid Driver's Phone Number Format")
    private String driverPhoneNumber;
    @Pattern(regexp = "^[+][0-9]{1,2}[0-9]{10}", message = "Invalid Conductor's Phone Number Format")
    private String conductorPhoneNumber;
    @Pattern(regexp = "^[A-Za-z ]{2,30}", message = "Invalid Source City Format")
    private String sourceCity;
    @Pattern(regexp = "^[0-9]{4}", message = "Invalid Start Time Format")
    private String startTime;
    @Pattern(regexp = "^[A-Za-z ]{2,30}", message = "Invalid Destination City Format")
    private String destinationCity;
    @Pattern(regexp = "^[0-9]{4}", message = "Invalid End Time Format")
    private String endTime;
    private String duration;
    private int maxSeats;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> occupiedSeats;
}
