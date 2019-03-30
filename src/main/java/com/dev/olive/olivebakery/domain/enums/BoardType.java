package com.dev.olive.olivebakery.domain.enums;

import com.dev.olive.olivebakery.exception.UserDefineException;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Getter
public enum BoardType {
    NOTICE("공지"), QUESTION("질문");

    private String boardExplain;

    BoardType(String boardExplain) {
        this.boardExplain = boardExplain;
    }

    /*
     * Client 쪽에서 Enum 타입이 아닌 String 타입으로 줬을 때
     */
    public static BoardType convertStrToEnum(String board) {
        if (board.equals(NOTICE.getBoardExplain())) {
            return NOTICE;
        }
        if (board.equals(QUESTION.getBoardExplain())) {
            return QUESTION;
        }
        throw new UserDefineException("해당 Enum 타입이 없습니다.");
    }
}