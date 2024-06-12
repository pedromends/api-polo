package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from Image i order by i.id asc limit 1")
    Image getlastInserted();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Image i SET i.code = :code WHERE i.id = :id")
    void updateCodeById(@Param("id")Long id, @Param("code")String code);
}
