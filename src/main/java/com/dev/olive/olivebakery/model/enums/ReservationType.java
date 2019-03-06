package com.dev.olive.olivebakery.model.enums;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-02-12.
 * 요청 -> 수락 -> 완료
 */

@Getter
public enum ReservationType {
    REQUEST("요청"), ACCEPT("수락"), COMPLETE("완료");

    private String reservationExplain;

    ReservationType(String reservationExplain) {
        this.reservationExplain = reservationExplain;
    }

    /*
     * Client 쪽에서 Enum 타입이 아닌 String 타입으로 줬을 때
     */
    public static ReservationType convertStrToEnum(String reservation) {
        if (reservation.equals(REQUEST.getReservationExplain())) {
            return REQUEST;
        } else if (reservation.equals(ACCEPT.getReservationExplain())) {
            return ACCEPT;
        } else {
            return COMPLETE;
        }
    }
}
