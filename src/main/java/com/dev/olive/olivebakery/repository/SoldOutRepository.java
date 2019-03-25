package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.SoldOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SoldOutRepository extends JpaRepository<SoldOut, Long> {
    List<SoldOut> findByDateEquals(LocalDate date);
}
