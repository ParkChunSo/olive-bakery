package com.dev.olive.olivebakery.domain.entity;

import com.dev.olive.olivebakery.domain.enums.DayType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Enumerated(value = EnumType.STRING)
    private DayType day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bread_id")
    private Bread bread;
}
