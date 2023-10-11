package com.travel.booking.repo;

import com.travel.booking.model.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatMapRepo extends JpaRepository<SeatMap, Integer> {
}
