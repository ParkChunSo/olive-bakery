package com.dev.olive.olivebakery.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor  // 빌더를 쓰려면 all, no 다 있어야 함
@NoArgsConstructor
@Table(name = "review_tbl")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "bread_id")
    private Bread bread;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;
}
