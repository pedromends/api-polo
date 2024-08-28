package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.PasswordRecovery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery, Long> {
}
