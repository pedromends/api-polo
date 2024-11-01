package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Researcher;
import com.ifmg.apipolo.entity.TalentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalentCardRepository extends JpaRepository<TalentCard, Long> {

    @Query("SELECT t from TalentCard t where t.active = true ORDER BY t.id DESC")
    List<TalentCard> activeOnes();
}
