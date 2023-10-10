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
    @ElementCollection(targetClass = SeatMap.class, fetch = FetchType.EAGER)
    private List<SeatMap> seatMaps;
    private String totalPrice;
}
