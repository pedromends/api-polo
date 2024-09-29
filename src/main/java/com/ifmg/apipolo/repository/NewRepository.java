package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewRepository extends JpaRepository<New, Long> {

    @Query("select mn from New mn WHERE mn.active = true ORDER BY mn.id DESC LIMIT 3")
    List<New> getLatestThree();

    @Query("select n from New n WHERE n.active = true ORDER BY n.id DESC")
    Page<New> findAllDesc(Pageable pageable);

    @Query("SELECT n FROM New n WHERE n.code LIKE %:searchTerm% AND n.active = true ORDER BY n.id DESC" )
    List<New> searchItems(@Param("searchTerm") String searchTerm);
}
