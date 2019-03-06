package com.dev.olive.olivebakery.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Getter
@Setter
public class ReservationSaveDto {

    private Timestamp bringTime;
    private String userId;
    private LinkedHashMap<String, Integer> breadInfo;//빵이름, 개수
    //    private List<String> breadNames;

    public ReservationSaveDto() {
    }

    @Builder
    public ReservationSaveDto(Timestamp bringTime, String userId, LinkedHashMap<String, Integer> breadInfo) {
        this.bringTime = bringTime;
        this.userId = userId;
        this.breadInfo = breadInfo;
    }

    public List<String> getBreadNames() {
        return new ArrayList<>(breadInfo.keySet());
    }

    public List<Integer> getBreadCounts() {
        return new ArrayList<>(breadInfo.values());
    }
}
