package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t where t.user.id = :id order by t.user.id limit 1")
    Token findByUserId(@Param("id")Long id);
}
