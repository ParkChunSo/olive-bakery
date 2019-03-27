package com.dev.olive.olivebakery.domain.dto;

import com.dev.olive.olivebakery.domain.entity.Board;
import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {
        private String context;
        private String title;
        private String userId;
        private String boardType;

        @Builder
        public Save(String context, String title, String userId, String boardType) {
            this.context = context;
            this.title = title;
            this.userId = userId;
            this.boardType = boardType;
        }

        public Board toEntity(Member member) {
            return Board.builder()
                    .context(context)
                    .title(title)
                    .member(member)
                    .boardType(BoardType.convertStrToEnum(boardType))
                    .build();
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
