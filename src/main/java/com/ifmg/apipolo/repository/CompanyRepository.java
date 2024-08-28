package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Company;
import com.ifmg.apipolo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where c.active = true")
    List<Company> findActives();
}
