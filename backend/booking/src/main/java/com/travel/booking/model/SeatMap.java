package com.travel.booking.model;

import com.travel.booking.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class SeatMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seatNumber;
    private String passengerName;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;

}
