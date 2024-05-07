package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.EventCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventCard, Long> {
}
