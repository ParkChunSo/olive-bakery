package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.model.dto.ReservationSaveDto;
import com.dev.olive.olivebakery.service.ReservationService;
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
    public void saveReservation(ReservationSaveDto reservationSaveDto) {
        reservationService.saveReservation(reservationSaveDto);
    }




}
