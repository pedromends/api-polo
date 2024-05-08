package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.EventCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCardRepository extends JpaRepository<EventCard, Long> {
}
