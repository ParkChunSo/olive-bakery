package com.dev.olive.olivebakery.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor  // 빌더를 쓰려면 all, no 다 있어야 함
@NoArgsConstructor
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bread bread;

    private String day;
}
