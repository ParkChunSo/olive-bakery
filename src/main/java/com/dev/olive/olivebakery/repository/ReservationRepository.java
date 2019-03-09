package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-02-09.
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
