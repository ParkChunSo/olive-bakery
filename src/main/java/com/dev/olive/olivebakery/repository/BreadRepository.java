package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.entity.Bread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadRepository extends JpaRepository<Bread, Long> {

    Bread findByName(String bread);
}
