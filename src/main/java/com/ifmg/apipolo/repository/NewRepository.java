package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewRepository extends JpaRepository<New, Long> {

    @Query("select mn from New mn ORDER BY mn.id DESC LIMIT 3")
    List<New> getLatestThree();

    @Query("select n from New n WHERE n.active = true ORDER BY n.id DESC")
    Page<New> findAllDesc(Pageable pageable);
}
