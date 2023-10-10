package com.travel.booking.model;

import com.travel.booking.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SeatMap {
    private int seatNumber;
    private String passengerName;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;

}
