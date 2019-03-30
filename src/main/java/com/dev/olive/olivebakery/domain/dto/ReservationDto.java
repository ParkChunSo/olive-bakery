package com.dev.olive.olivebakery.domain.dto;

import com.dev.olive.olivebakery.domain.enums.ReservationType;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        private LocalDateTime reservationTime;
        private LocalDateTime bringTime;
        private int price;
        private String memberName;
        private List<ReservationBread> reservationBreads = new ArrayList<>();

        @Builder
        public GetDto(Long reservationId, LocalDateTime reservationTime, LocalDateTime bringTime, int price, String memberName, List<ReservationBread> reservationBreads) {
            this.reservationId = reservationId;
            this.reservationTime = reservationTime;
            this.bringTime = bringTime;
            this.price = price;
            this.memberName = memberName;
            this.reservationBreads = reservationBreads;
        }

        public void setReservationBreads(List<ReservationBread> reservationBreads) {
            this.reservationBreads = reservationBreads;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class GetTmpDto {

        private Long reservationId;
        private LocalDateTime reservationTime;
        private LocalDateTime bringTime;
        private int price;
        private String memberName;
        private String breadName;
        private int breadCount;

        @Builder
        public GetTmpDto(Long reservationId, LocalDateTime reservationTime, LocalDateTime bringTime, int price, String memberName, String breadName, int breadCount) {
            this.reservationId = reservationId;
            this.reservationTime = reservationTime;
            this.bringTime = bringTime;
            this.price = price;
            this.memberName = memberName;
            this.breadName = breadName;
            this.breadCount = breadCount;
        }

        @Override
        public String toString() {
            return "GetTmpDto{" +
                    "reservationId=" + reservationId +
                    ", reservationTime=" + reservationTime +
                    ", bringTime=" + bringTime +
                    ", price=" + price +
                    ", memberName='" + memberName + '\'' +
                    ", breadName='" + breadName + '\'' +
                    ", breadCount=" + breadCount +
                    '}';
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

        private LocalDateTime bringTime;
        private String userEmail;
        private LinkedHashMap<String, Integer> breadInfo;

        @Builder
        public SaveDto(LocalDateTime bringTime, String userEmail, LinkedHashMap<String, Integer> breadInfo) {
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DateRequestDto {

        private ReservationType reservationType;
        private LocalDate selectDate;

        @Builder
        public DateRequestDto(ReservationType reservationType, LocalDate selectDate) {
            this.reservationType = reservationType;
            this.selectDate = selectDate;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DateRangeRequestDto {

        private ReservationType reservationType;
        private LocalDate startDate;
        private LocalDate endDate;

        @Builder
        public DateRangeRequestDto(ReservationType reservationType, LocalDate startDate, LocalDate endDate) {
            this.reservationType = reservationType;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }


}
