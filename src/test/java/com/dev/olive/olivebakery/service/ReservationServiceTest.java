package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.domain.enums.ReservationType;
import com.dev.olive.olivebakery.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @Test
    public void findById() {
    }

    @Test
    public void saveReservation() throws Exception {

        //given
        LinkedHashMap<String, Integer> maps = new LinkedHashMap<>();
        maps.put("생일빵", 4);//빵-개수
        maps.put("죽빵", 6);//빵-개수

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ReservationDto.SaveDto saveDto = ReservationDto.SaveDto.builder()
                .bringTime(timestamp)
                .userEmail("testemail")
                .breadInfo(maps)
                .build();

        //when
        reservationService.saveReservation(saveDto);
    }

    @Test
    public void updateReservationType() {
    }

    @Test
    public void getReservationInfoByUserId() throws Exception {

        //given
        final String email = "testemail";
        final ReservationType reservationType = ReservationType.REQUEST;

        //when
        List<ReservationDto.GetDto> getDtos = reservationService.getReservationInfoByUserId(email, reservationType);

        //then
        getDtos.stream().forEach(s -> System.out.println(s.toString()));
    }
}