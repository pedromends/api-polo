package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
