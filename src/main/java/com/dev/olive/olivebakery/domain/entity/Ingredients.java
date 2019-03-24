package com.dev.olive.olivebakery.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Ingredients {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredients_id")
    private Long id;

    private String name;

    private String origin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bread_id")
    private Bread bread;
}
