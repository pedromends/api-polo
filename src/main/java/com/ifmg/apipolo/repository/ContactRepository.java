package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.Contact;
import com.ifmg.apipolo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    @Query("select c from Contact c where c.readed = false")
    List<Contact> findUnreaded();

    @Query("select c from Contact c order by c.readed asc")
    List<Contact> findAllCustom();
}
