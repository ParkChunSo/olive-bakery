package com.dev.olive.olivebakery.domain.dto;


import com.dev.olive.olivebakery.domain.enums.DayType;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BreadDto {

    private String name;
    private Integer price;
    private String picturePath;
    private String description;
    private Boolean soldOut;
    private Integer star;
    private DayType days;

}
