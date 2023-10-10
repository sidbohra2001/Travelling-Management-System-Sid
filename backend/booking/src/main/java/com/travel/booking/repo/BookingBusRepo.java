package com.travel.booking.repo;

import com.travel.booking.model.BookingBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingBusRepo extends JpaRepository<BookingBus, String> {
}
