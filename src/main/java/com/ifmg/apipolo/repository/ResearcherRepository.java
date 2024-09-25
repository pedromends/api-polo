package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResearcherRepository extends JpaRepository<Researcher, Long> {

    @Modifying
    @Query("DELETE from Researcher r where r.id = :id")
    void myDeleteById(@Param("id") Long id);

    @Query("SELECT r from Researcher r where r.email = :email")
    Researcher getByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE Researcher r SET r.img = null WHERE r.id = :id")
    void resetImg(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Researcher r SET r.campus = null WHERE r.id = :id")
    void resetCampus(@Param("id") Long id);

    @Query("SELECT r from Researcher r where r.active = true ORDER BY r.id DESC")
    List<Researcher> activeOnes();
}
