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
    @GetMapping("/userId/{userId}/type/{type}")
    public List<ReservationDto.GetDto> getReservationInfos(@PathVariable("userId") String userId, @PathVariable("type") ReservationType reservationType) {
        return reservationService.getReservationInfos(userId, reservationType);
    }

    @ApiOperation("유저의 가장 최근 예약내역을 예약타입에 무관하게 조회")
    @PostMapping("/userId/{userId}/recently")
    public ReservationDto.GetDto getReservationInfosByDate(@PathVariable("userId") String userId) {
        return reservationService.getReservationInfoByRecently(userId);
    }

    @ApiOperation("날짜별 예약 조회, Admin 에서 사용")
    @PostMapping("/date")
    public List<ReservationDto.GetDto> getReservationInfosByDate(@RequestBody ReservationDto.DateRequestDto dateRequestDto) {
        return reservationService.getReservationInfosByDate(dateRequestDto);
    }

    @ApiOperation("날짜구간별 예약 조회, Admin 에서 사용")
    @PostMapping("/date/range")
    public List<ReservationDto.GetDto> getReservationInfosByDateRange(@RequestBody ReservationDto.DateRangeRequestDto dateRangeRequestDto) {
        return reservationService.getReservationInfosByDateRange(dateRangeRequestDto);
    }

    @ApiOperation("예약 정보 저장")
    @PostMapping
    public void saveReservation(@RequestBody ReservationDto.SaveDto saveDto) {
        reservationService.saveReservation(saveDto);
    }

    @ApiOperation("예약정보 수정")
    @PutMapping
    public void updateReservation(@RequestBody ReservationDto.UpdateDto updateDto) {
        reservationService.updateReservation(updateDto);
    }

    @ApiOperation("예약정보 삭제")
    @DeleteMapping("/{num}")
    public void deleteReservation(@PathVariable("num") Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @ApiOperation("예약 상태 수정")
    @PutMapping("/{num}")
    public void updateReservationType(@PathVariable("num") Long reservationId) {
        reservationService.updateReservationType(reservationId);
    }

}
