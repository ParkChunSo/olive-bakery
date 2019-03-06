package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.model.dto.ReservationDto;
import com.dev.olive.olivebakery.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public void saveReservation(ReservationDto.Save saveDto) {
        reservationService.saveReservation(saveDto);
    }

    @GetMapping
    public void updateReservationType() {
        reservationService.updateReservationType();
    }


}
