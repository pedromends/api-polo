package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
