package com.dev.olive.olivebakery.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Getter
@Setter
public class BoardUpdateDto {

    private Long boardId;
    private String context;
    private String title;

    public BoardUpdateDto() {
    }

    @Builder
    public BoardUpdateDto(Long boardId, String context, String title) {
        this.boardId = boardId;
        this.context = context;
        this.title = title;
    }
}
