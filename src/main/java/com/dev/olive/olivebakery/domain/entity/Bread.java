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
@Builder
@AllArgsConstructor
public class Bread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long breadId;

    private String name;

    private Integer price;

    private String picturePath;

    //상세정보가 아닌 간단한 소개(리스트에서 보내줄 것)
    private String description;

    //빵을 클릭했을 때 선택한 빵의 상세 소개
    private String detailDescription;

    private Boolean isSoldOut;

    private Integer star;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "bread_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredient> ingredients = new ArrayList<>();

    // TODO(추가해야할 컬럼 조사)

    @OneToMany(mappedBy = "bread")
    private List<Days> days = new ArrayList<>();

    @OneToMany(mappedBy = "bread")
    private List<ReservationInfo> reservationInfos = new ArrayList<>();
}
