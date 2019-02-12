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
@Table(name = "user_tbl")
public class User {

    @Id
    private String userId;

    private String userPw;

    private String name;

    private String phoneNum;

    private String email;

    private Integer stamp = 0;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Board> boards;

}
