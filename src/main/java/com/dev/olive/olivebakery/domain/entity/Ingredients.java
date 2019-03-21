package com.dev.olive.olivebakery.domain.entity;

import javax.persistence.*;

@Entity
public class Ingredients {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredients_id")
    private Long id;

    private String name;

    private String origin;
}