package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.domain.dto.ReservationDto.GetTmpDto;
import com.dev.olive.olivebakery.domain.entity.Reservation;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.domain.enums.ReservationType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by YoungMan on 2019-02-09.
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<List<Reservation>> findByMember(String email);

    @Query(value = "select new com.dev.olive.olivebakery.domain.dto.ReservationDto$GetTmpDto(reservation.reservationId, reservation.reservationTime, reservation.bringTime, reservation.price, memeber.name, " +
            "bread.name, reservationinfos.breadCount) " +
            "from Reservation reservation " +
            "join reservation.member memeber " +
            "join reservation.reservationInfos reservationinfos " +
            "join reservationinfos.bread bread " +
            "where memeber.email = :email and reservation.reservationType = :reservationType")
    List<ReservationDto.GetTmpDto> getReservationInfos(@Param("email") String email, @Param("reservationType") ReservationType reservationType);


   /* @Query(value = "select new com.dev.olive.olivebakery.domain.dto.ReservationDto.GetTmpDto(reservation.reservationId, reservation.reservationTime, reservation.bringTime, reservation.price, memeber.name, " +
            "bread.name, reservationinfos.breadCount) " +
            "from Reservation reservation " +
            "join reservation.member memeber " +
            "join reservation.reservationInfos reservationinfos " +
            "join reservationinfos.bread bread " +
            "where memeber.email = :email " +
            "order by reservation.reservationTime desc")
    ReservationDto.GetTmpDto getReservationInfoByRecently(@Param("email") String email, Pageable pageable);*/


    @Query(value = "SELECT new com.dev.olive.olivebakery.domain.dto.ReservationDto$GetTmpDto(reservation.reservationId, reservation.reservationTime, reservation.bringTime, reservation.price, memeber.name, " +
            "bread.name, reservationinfos.breadCount) " +
            "FROM Reservation reservation " +
            "JOIN reservation.member memeber " +
            "JOIN reservation.reservationInfos reservationinfos " +
            "JOIN reservationinfos.bread bread " +
            "WHERE reservation.reservationType = :reservationType AND reservation.reservationTime > :startDate AND reservation.reservationTime < :endDate ")
    List<GetTmpDto> getReservationInfosByDate(@Param("reservationType") ReservationType reservationType, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}















  /*  @Query(value = "select reservation.reservationId, reservation.reservationTime, reservation.bringTime, reservation.price, memeber.name, " +
            "bread.name, reservationinfos.breadCount " +
            "from Reservation reservation " +
            "join reservation.member memeber " +
            "join reservation.reservationInfos reservationinfos " +
            "join reservationinfos.bread bread " +
            "where memeber.email = :email and reservation.reservationType = :reservationType")
    List<Object[]> getReservationInfos(@Param("email") String email, @Param("reservationType") ReservationType reservationType);*/


 /*@Query(value = "select reservation.reservationId, reservation.reservationTime, reservation.bringTime, reservation.price, memeber.name, " +
            "bread.name, reservationinfos.breadCount " +
            "from Reservation reservation " +
            "join reservation.member memeber " +
            "join reservation.reservationInfos reservationinfos " +
            "join reservationinfos.bread bread " +
            "where reservation.reservationType = :reservationType and reservation.reservationTime > :startDate and reservation.reservationTime < :endDate ")
    List<Object[]> getReservationInfosByDate(@Param("reservationType") ReservationType reservationType, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);*/