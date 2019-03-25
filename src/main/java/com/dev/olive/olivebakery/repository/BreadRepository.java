package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.Bread;
import com.dev.olive.olivebakery.domain.enums.DayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by YoungMan on 2019-02-09.
 */

public interface BreadRepository extends JpaRepository<Bread, Long> {
  
  //Bread findByName(String breadName);

    Optional<Bread> findByName(@Param("breadName") String breadName);

    List<Bread> findByDays(DayType dayType);

    List<Bread> findByNameIn(@Param("breadNames") List<String> breadName);
}
