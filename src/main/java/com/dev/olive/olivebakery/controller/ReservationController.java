package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.domain.enums.ReservationType;
import com.dev.olive.olivebakery.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 * 1. 오늘 날짜 예약 정보만 가져오기.(userId, reservationType)
 * 2. 예약 번호 발급 - AutoIncrease
 * 3. 예약할때 validation check
 * 4.
 */

@RestController
@RequestMapping(value = "/olive/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ApiOperation("user 가 예약한 전체 내역 조회")
    @GetMapping("/{userId}/type/{type}")
    public List<ReservationDto.GetDto> getReservation(@PathVariable("userId") String userId, @PathVariable("type") ReservationType reservationType){
        return reservationService.getReservationInfoByUserId(userId, reservationType);
    }

    @ApiOperation("예약 정보 저장")
    @PostMapping
    public void saveReservation(@RequestBody ReservationDto.SaveDto saveDto) {
        reservationService.saveReservation(saveDto);
    }

    @ApiOperation("예약정보 수정")
    @PutMapping
    public void updateReservation(@RequestBody ReservationDto.UpdateDto updateDto){
        reservationService.updateReservation(updateDto);
    }

    @ApiOperation("예약정보 삭제")
    @DeleteMapping("/{num}")
    public void deleteReservation(@PathVariable("num") Long reservationId){
        reservationService.deleteReservation(reservationId);
    }

    @ApiOperation("예약 상태 수정")
    @PutMapping("/{num}")
    public void updateReservationType(@PathVariable("num") Long reservationId) {
        reservationService.updateReservationType(reservationId);
    }


}
