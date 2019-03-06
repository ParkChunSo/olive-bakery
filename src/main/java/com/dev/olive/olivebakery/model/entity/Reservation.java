package com.dev.olive.olivebakery.model.entity;

import com.dev.olive.olivebakery.model.enums.ReservationType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
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

    private String reservationType = ReservationType.REQUEST.toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "reservation")
    private List<ReservationInfo> reservationInfos;

    @Builder
    public Reservation(Timestamp bringTime, Integer price, User user, List<ReservationInfo> reservationInfos) {
        this.bringTime = bringTime;
        this.price = price;
        this.user = user;
        this.reservationInfos = reservationInfos;
        this.reservationType = ReservationType.REQUEST.toString();
    }
}
