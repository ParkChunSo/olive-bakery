package com.dev.olive.olivebakery.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-08.
 */

@Entity
@Table(name = "user_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String userId;

    private String userPw;

    private String name;

    private String phoneNum;

    private String email;

    private Integer stamp = 0;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

}
