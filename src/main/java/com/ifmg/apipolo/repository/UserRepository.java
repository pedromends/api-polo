package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email")String email);

    @Query("select u from User u join Token t on u.id = t.user.id where t.token = :substring ORDER BY u.id LIMIT 1")
    User findByTokenCode(@Param("substring")String substring);
}
//order by u.id LIMIT 1