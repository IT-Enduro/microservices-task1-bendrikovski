package com.education.ticketsservice.repository;

import com.education.ticketsservice.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    TicketEntity findByTicketUidAndName(UUID ticketUid, String xUserName);
}
