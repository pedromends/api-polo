package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.PresentationCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenterCardRepository extends JpaRepository<PresentationCard, Long> {
}
