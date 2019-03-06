package com.dev.olive.olivebakery.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Entity
@Table(name = "days_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dayId;

    private String day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bread_id")
    private Bread bread;
}
