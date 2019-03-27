package com.dev.olive.olivebakery.domain.dto;

import com.dev.olive.olivebakery.domain.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class CommentDto {

    @Getter @NoArgsConstructor
    public static class Save{
        private String boardId;
        private String name;
        private String content;

        public Comment toEntity(){
            return Comment.builder()
                    .name(name)
                    .content(content)
                    .build();
        }
    }

    @Getter @NoArgsConstructor
    public static class Update{
        private String boardId;
        private String commentId;
        private String name;
        private String content;

        public Comment toEntity(){
            return Comment.builder()
                    .name(name)
                    .content(content)
                    .build();
        }
    }
}
