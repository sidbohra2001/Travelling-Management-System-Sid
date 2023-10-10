package com.travel.booking.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SeatDto {
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[ ]?[A-Z]{2}[0-9]{4}$", message = "Invalid registration number format")
    private String registrationNumber;
    private List<Integer> seatNumbers;
}
