package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p where p.active = true")
    List<Project> findActives();
}
