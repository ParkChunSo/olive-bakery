package com.dev.olive.olivebakery.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Getter
@Setter
@Entity
@Table(name = "bread_tbl")
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

    @OneToMany(mappedBy = "bread")
    private List<Days> days;

    @OneToMany(mappedBy = "bread")
    private List<ReservationInfo> reservationInfos;

}
