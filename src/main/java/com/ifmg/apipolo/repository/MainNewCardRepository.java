package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.vo.NewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MainNewCardRepository extends JpaRepository<MainNewCard, Long> {


}
