package com.dev.olive.olivebakery.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDto {
    private String name;

    private String origin;
}
