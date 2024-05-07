package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.TalentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<TalentCard, Long> {
}
