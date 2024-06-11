package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from Image i order by i.id asc limit 1")
    Image getlastInserted();
}
