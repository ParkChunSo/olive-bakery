package com.dev.olive.olivebakery.service.reservation;

import com.dev.olive.olivebakery.model.dto.ReservationSaveDto;
import com.dev.olive.olivebakery.model.entity.Bread;
import com.dev.olive.olivebakery.model.entity.Reservation;
import com.dev.olive.olivebakery.model.entity.ReservationInfo;
import com.dev.olive.olivebakery.model.entity.User;
import com.dev.olive.olivebakery.repository.ReservationInfoRepository;
import com.dev.olive.olivebakery.service.bread.BreadFindService;
import com.dev.olive.olivebakery.service.bread.BreadService;
import com.dev.olive.olivebakery.service.user.UserFindService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-09.
 */

@Service
public class ReservationService {
    private final ReservationInfoRepository reservationInfoRepository;
    private UserFindService userFindService;
    private BreadFindService breadFindService;
    private BreadService breadService;

    public ReservationService(ReservationInfoRepository reservationInfoRepository, UserFindService userFindService, BreadFindService breadFindService, BreadService breadService) {
        this.reservationInfoRepository = reservationInfoRepository;
        this.userFindService = userFindService;
        this.breadFindService = breadFindService;
        this.breadService = breadService;
    }

    public void saveReservation(ReservationSaveDto reservationSaveDto) {
        reservationInfoRepository.saveAll(convertSaveDtoToEntity(reservationSaveDto));
    }

    private List<ReservationInfo> convertSaveDtoToEntity(ReservationSaveDto reservationSaveDto) {
        List<ReservationInfo> reservationInfos = new ArrayList<>();
        User user = userFindService.findById(reservationSaveDto.getUserId());
        List<Bread> breads = breadFindService.findsByNames(reservationSaveDto.getBreadNames());
        int finalPrice = breadService.getFinalPrice(reservationSaveDto.getBreadInfo());
        //        int finalPrice = breadService.getFinalPrice(breads);

        Reservation reservation = Reservation.builder()
                .bringTime(reservationSaveDto.getBringTime())
                .user(user)
                .price(finalPrice)
                .build();

        for (int i = 0; i < breads.size(); i++) {
            reservationInfos.add(ReservationInfo.builder()
                    .breadCount(reservationSaveDto.getBreadCounts().get(i))
                    .bread(breads.get(i))
                    .reservation(reservation)
                    .build());
        }

        return reservationInfos;
    }


}
