package com.dev.olive.olivebakery.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Entity
@Table(name = "reservationinfo_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public ReservationInfo(Integer breadCount, Reservation reservation, Bread bread) {
        this.breadCount = breadCount;
        this.reservation = reservation;
        this.bread = bread;
    }

}
