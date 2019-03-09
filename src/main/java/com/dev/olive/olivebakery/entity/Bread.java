package com.dev.olive.olivebakery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor  // 빌더를 쓰려면 all, no 다 있어야 함
@NoArgsConstructor
public class Bread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int price;
    private String picturePath;
    private String description;
    private int soldOut;
    private int star;
    private String day;
}