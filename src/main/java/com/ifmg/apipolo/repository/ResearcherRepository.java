package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
}
