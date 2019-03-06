package com.dev.olive.olivebakery.model.dto;

import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.model.enums.BoardType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-03-05.
 */

public class BoardDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {
        private String context;
        private String title;
        private String userId;
        private BoardType boardType;

        @Builder
        public Save(String context, String title, String userId, BoardType boardType) {
            this.context = context;
            this.title = title;
            this.userId = userId;
            this.boardType = boardType;
        }

        public Board toEntity() {
            return Board.builder()
                    .context(context)
                    .title(title)
                    .user()
                    .boardType()
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Update {
        private Long boardId;
        private String context;
        private String title;

        @Builder
        public Update(Long boardId, String context, String title) {
            this.boardId = boardId;
            this.context = context;
            this.title = title;
        }
    }
}
