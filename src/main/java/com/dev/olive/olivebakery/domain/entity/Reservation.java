package com.dev.olive.olivebakery.domain.entity;

import com.dev.olive.olivebakery.domain.enums.ReservationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Entity
@Table(name = "reservation_tbl")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @CreatedDate
    private Timestamp reservationTime;

    private Timestamp bringTime;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member member;

    @OneToMany(mappedBy = "reservation")
    private List<ReservationInfo> reservationInfos = new ArrayList<>();

    @Builder
    public Reservation(Timestamp reservationTime, Timestamp bringTime, Integer price, Member member, List<ReservationInfo> reservationInfos) {
        this.reservationTime = reservationTime;
        this.bringTime = bringTime;
        this.price = price;
        this.reservationType = ReservationType.REQUEST;
        this.member = member;
        this.reservationInfos = reservationInfos;
    }

    /*
     * 요청 -> 수락
     * 수락 -> 완료
     */
    public void updateReservationType() {
        reservationType = reservationType.equals(ReservationType.REQUEST) ? ReservationType.ACCEPT : ReservationType.COMPLETE;
    }
}
