package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Talent;
import com.ifmg.apipolo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
