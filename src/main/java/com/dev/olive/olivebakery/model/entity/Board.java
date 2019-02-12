package com.dev.olive.olivebakery.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Getter
@Setter
@Entity
@Table(name = "board_tbl")
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @CreatedDate
    private Timestamp insertTime;

    @LastModifiedDate
    private Timestamp updateTime;

    private String title;

    @Lob
    private String context;

    private String boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany//단방향
    @JoinColumn(name = "board_id")
    private List<Comment> comments;

    public Board() {
    }

    @Builder
    public Board(String title, String context, String boardType, User user) {
        this.title = title;
        this.context = context;
        this.boardType = boardType;
        this.user = user;
    }


}
