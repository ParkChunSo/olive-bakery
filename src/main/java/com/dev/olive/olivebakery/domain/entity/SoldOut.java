package com.dev.olive.olivebakery.domain.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sold_out_tbl")
public class SoldOut {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date date;

    private Bread bread;
}
