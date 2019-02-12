package com.dev.olive.olivebakery.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Getter
@Setter
@Entity
@Table(name = "reservationinfo_tbl")
public class ReservationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationInfoId;

    private Integer breadCount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bread_id")
    private Bread bread;

    public ReservationInfo() {
    }

    @Builder
    public ReservationInfo(Integer breadCount, Reservation reservation, Bread bread) {
        this.breadCount = breadCount;
        this.reservation = reservation;
        this.bread = bread;
    }

}
