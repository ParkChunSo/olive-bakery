package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.Reservation;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.domain.enums.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by YoungMan on 2019-02-09.
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<List<Reservation>> findByMember(String email);

    @Query(value = "select reservation.reservationId, reservation.reservationTime, reservation.bringTime, reservation.price, memeber.name, " +
            "bread.name, reservationinfos.breadCount " +
            "from Reservation reservation " +
            "join reservation.member memeber " +
            "join reservation.reservationInfos reservationinfos " +
            "join reservationinfos.bread bread " +
            "where memeber.email = :email and reservation.reservationType = :reservationType")
    List<Object[]> getReservationInfoByUserId(@Param("email") String email, @Param("reservationType") ReservationType reservationType);
}
