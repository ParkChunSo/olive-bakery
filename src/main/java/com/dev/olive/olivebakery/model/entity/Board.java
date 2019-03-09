package com.dev.olive.olivebakery.model.entity;

import com.dev.olive.olivebakery.model.dto.BoardDto;
import com.dev.olive.olivebakery.model.enums.BoardType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Entity
@Table(name = "board_tbl")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany//단방향
    @JoinColumn(name = "board_id")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String title, String context, BoardType boardType, User user) {
        this.title = title;
        this.context = context;
        this.boardType = boardType;
        this.user = user;
    }

    public void updateBoard(BoardDto.Update updateDto) {
        this.context = updateDto.getContext();
        this.title = updateDto.getTitle();
    }


}
