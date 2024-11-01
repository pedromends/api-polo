package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t where t.user.id = :id and t.expired = false order by t.user.id asc")
    Token findByUserId(@Param("id") Long id);

    @Query("select t from Token t where t.token = :token and t.expired = false")
    Token findByTokenCode(@Param("token") String tokenCode);
}