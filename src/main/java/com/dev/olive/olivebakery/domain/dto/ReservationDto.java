package com.dev.olive.olivebakery.domain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by YoungMan on 2019-03-06.
 * breadInfo : 빵이름, 개수
 */

@SuppressWarnings("Duplicates")
public class ReservationDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString(exclude = "reservationBreads")
    public static class GetDto {

        private Long reservationId;
        private Timestamp reservationTime;
        private Timestamp bringTime;
        private int price;
        private String memberName;
        private List<ReservationBread> reservationBreads = new ArrayList<>();

        @Builder
        public GetDto(Long reservationId, Timestamp reservationTime, Timestamp bringTime, int price, String memberName, List<ReservationBread> reservationBreads) {
            this.reservationId = reservationId;
            this.reservationTime = reservationTime;
            this.bringTime = bringTime;
            this.price = price;
            this.memberName = memberName;
            this.reservationBreads = reservationBreads;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class GetTmpDto {

        private Long reservationId;
        private Timestamp reservationTime;
        private Timestamp bringTime;
        private int price;
        private String memberName;
        private String breadName;
        private int breadCount;

        @Builder
        public GetTmpDto(Long reservationId, Timestamp reservationTime, Timestamp bringTime, int price, String memberName, String breadName, int breadCount) {
            this.reservationId = reservationId;
            this.reservationTime = reservationTime;
            this.bringTime = bringTime;
            this.price = price;
            this.memberName = memberName;
            this.breadName = breadName;
            this.breadCount = breadCount;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReservationBread {

        private String breadName;
        private int breadCount;

        @Builder
        public ReservationBread(String breadName, int breadCount) {
            this.breadName = breadName;
            this.breadCount = breadCount;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveDto {

        private Timestamp bringTime;
        private String userEmail;
        private LinkedHashMap<String, Integer> breadInfo;

        @Builder
        public SaveDto(Timestamp bringTime, String userEmail, LinkedHashMap<String, Integer> breadInfo) {
            this.bringTime = bringTime;
            this.userEmail = userEmail;
            this.breadInfo = breadInfo;
        }

        public List<String> getBreadNames() {
            return new ArrayList<>(breadInfo.keySet());
        }

        public List<Integer> getBreadCounts() {
            return new ArrayList<>(breadInfo.values());
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateDto {

        private Long reservationId;
        private SaveDto saveDto;

        @Builder
        public UpdateDto(Long reservationId, SaveDto saveDto) {
            this.reservationId = reservationId;
            this.saveDto = saveDto;
        }
    }
}
