package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
}
