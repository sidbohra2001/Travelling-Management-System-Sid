package com.travel.bus.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SeatDto {
    private String registrationNumber;
    private List<Integer> seatNumbers;
}
