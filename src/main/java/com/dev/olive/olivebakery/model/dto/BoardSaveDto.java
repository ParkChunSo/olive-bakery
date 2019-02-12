package com.dev.olive.olivebakery.model.dto;

import com.dev.olive.olivebakery.model.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Getter
@Setter
public class BoardSaveDto{

    private String context;
    private String title;
    private String userId;
    private BoardType boardType;

    public BoardSaveDto() {
    }

    @Builder
    public BoardSaveDto(String context, String title, BoardType boardType, String userId) {
        this.context = context;
        this.title = title;
        this.boardType = boardType;
        this.userId = userId;
    }

}
