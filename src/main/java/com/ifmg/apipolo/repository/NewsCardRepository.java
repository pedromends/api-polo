package com.ifmg.apipolo.repository;

import com.ifmg.apipolo.entity.NewsCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCardRepository extends JpaRepository<NewsCard, Long> {
}
