package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.entity.*;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.repository.ReservationInfoRepository;
import com.dev.olive.olivebakery.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-09.
 */

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationInfoRepository reservationInfoRepository;
    private SignService signService;
    private BreadService breadService;

    public ReservationService(ReservationRepository reservationRepository, ReservationInfoRepository reservationInfoRepository, SignService signService, BreadService breadService) {
        this.reservationRepository = reservationRepository;
        this.reservationInfoRepository = reservationInfoRepository;
        this.signService = signService;
        this.breadService = breadService;
    }

    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new UserDefineException("해당 예약내역이 없습니다."));
    }

    public void saveReservation(ReservationDto.Save saveDto) {
        reservationInfoRepository.saveAll(convertSaveDtoToEntity(saveDto));
    }

    private List<ReservationInfo> convertSaveDtoToEntity(ReservationDto.Save saveDto) {
        List<ReservationInfo> reservationInfos = new ArrayList<>();
        Member member = signService.findById(saveDto.getUserId());
        List<Bread> breads = breadService.findsByNames(saveDto.getBreadNames());
        int finalPrice = breadService.getFinalPrice(saveDto.getBreadInfo());

        Reservation reservation = Reservation.builder()
                .bringTime(saveDto.getBringTime())
                .member(member)
                .price(finalPrice)
                .build();

        for (int i = 0; i < breads.size(); i++) {
            reservationInfos.add(ReservationInfo.builder()
                    .breadCount(saveDto.getBreadCounts().get(i))
                    .bread(breads.get(i))
                    .reservation(reservation)
                    .build());
        }

        return reservationInfos;
    }

    public void updateReservationType(Long reservationId) {
        Reservation reservation = findById(reservationId);
        reservation.updateReservationType();
        reservationRepository.save(reservation);
    }


}
