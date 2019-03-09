package com.dev.olive.olivebakery.dto;


import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BreadDto {

    private String name;
    private int price;
    private String picturePath;
    private String description;
    private int soldOut;
    private int star;
    private String days;

}
