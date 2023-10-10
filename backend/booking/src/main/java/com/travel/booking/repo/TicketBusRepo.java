package com.travel.booking.repo;

import com.travel.booking.model.TicketBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBusRepo extends JpaRepository<TicketBus, String> {
}
