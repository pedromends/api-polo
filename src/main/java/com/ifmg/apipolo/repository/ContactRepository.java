package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
