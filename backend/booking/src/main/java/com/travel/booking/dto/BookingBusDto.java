package com.travel.booking.dto;

import com.travel.booking.enums.Transport;
import com.travel.booking.model.SeatMap;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookingBusDto {
    @Enumerated(EnumType.STRING)
    private Transport transport;
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[ ]?[A-Z]{2}[0-9]{4}$", message = "Invalid registration number format")
    private String registrationNumber;
    @Pattern(regexp = "^[A-Za-z ]{2,30}", message = "Invalid Source City Format")
    private String sourceCity;
    @Pattern(regexp = "^[A-Za-z ]{2,30}", message = "Invalid Destination City Format")
    private String destinationCity;
    @Pattern(regexp = "^[0-9]{4}", message = "Invalid Start Time Format")
    private String startTime;
    @Pattern(regexp = "^[0-9]{4}", message = "Invalid End Time Format")
    private String endTime;
    @Pattern(regexp = "^[0-9]{1,4}", message = "Invalid price per seat format")
    private String pricePerSeat;
    @ElementCollection(targetClass = SeatMap.class, fetch = FetchType.EAGER)
    private List<SeatMap> seatMaps;
    private String totalPrice;
}
