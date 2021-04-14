package com.furkannsahin.adison.repository;

import com.furkannsahin.adison.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AdRepository extends JpaRepository<Ad, Long> {
}
