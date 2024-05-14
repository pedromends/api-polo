package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
