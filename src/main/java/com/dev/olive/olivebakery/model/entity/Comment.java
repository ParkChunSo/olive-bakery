package com.dev.olive.olivebakery.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Getter
@Setter
@Entity
@Table(name = "comment_tbl")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Lob
    private String content;

}
