package com.dev.olive.olivebakery.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-12.
 */

@Getter
@Setter
@Entity
@Table(name = "favorite_tbl")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bread_id")
    private Bread bread;

    private Integer page;
}
