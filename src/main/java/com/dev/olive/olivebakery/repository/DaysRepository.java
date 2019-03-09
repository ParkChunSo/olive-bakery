package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.entity.Bread;
import com.dev.olive.olivebakery.entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaysRepository extends JpaRepository<Days, Long> {

    List<Days> findByDay(String day);

    @Query(value = "select day.bread from Days as day where day.day = :findDay")
    List<Bread> findBread(@Param("findDay") String day);
}