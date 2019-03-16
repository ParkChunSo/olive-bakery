package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-13.
 */

@RestController
@RequestMapping(value = "/olive/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ApiOperation("user가 예약한 전체 내역 조회")
    @GetMapping("/{userId}")
    public List<ReservationDto.Get> getReservation(@PathVariable String userId){
        return reservationService.getReservationInfoByUserId(userId);
    }

    @ApiOperation("예약 정보 저장")
    @PostMapping
    public void saveReservation(@RequestBody ReservationDto.Save saveDto) {
        reservationService.saveReservation(saveDto);
    }

    @ApiOperation("예약 정보 수정")
    @PutMapping("/{num}")
    public void updateReservationType(@PathVariable("num") Long reservationId) {
        reservationService.updateReservationType(reservationId);
    }


}
