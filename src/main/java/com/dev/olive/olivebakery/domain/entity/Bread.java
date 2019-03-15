package com.dev.olive.olivebakery.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Entity
@Table(name = "bread_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long breadId;

    private String name;

    private Integer price;

    private String picturePath;

    private String description;

    private Boolean isSoldOut;

    private Integer star;

    // TODO(추가해야할 컬럼 조사)

    @OneToMany(mappedBy = "bread")
    private List<Days> days = new ArrayList<>();

    @OneToMany(mappedBy = "bread")
    private List<ReservationInfo> reservationInfos = new ArrayList<>();

    @Builder
    public Bread(String name, Integer price, String picturePath, String description, Boolean isSoldOut, Integer star, List<Days> days, List<ReservationInfo> reservationInfos) {
        this.name = name;
        this.price = price;
        this.picturePath = picturePath;
        this.description = description;
        this.isSoldOut = isSoldOut;
        this.star = star;
        this.days = days;
        this.reservationInfos = reservationInfos;
    }
}
