package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by YoungMan on 2019-02-09.
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findByMember(Member member);
}
