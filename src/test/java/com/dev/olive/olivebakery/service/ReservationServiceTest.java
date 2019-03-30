package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.domain.enums.ReservationType;
import com.dev.olive.olivebakery.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        LocalDateTime bringTime = LocalDateTime.now();

        ReservationDto.SaveDto saveDto = ReservationDto.SaveDto.builder()
                .bringTime(bringTime)
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
    public void getReservationInfos() throws Exception {

        //given
        final String email = "testemail";
        final ReservationType reservationType = ReservationType.REQUEST;

        //when
        List<ReservationDto.GetDto> getDtos = reservationService.getReservationInfos(email, reservationType);

        //then
        getDtos.stream().forEach(s -> System.out.println(s.toString()));
    }

    @Test
    public void getReservationInfoRecently() throws Exception {

        //given
        final String email = "testemail";

       /* //when
        ReservationDto.GetTmpDto getDto = reservationService.getReservationInfoByRecently(email);

        //then
        System.out.println(getDto.toString());*/
    }

    @Test
    public void getReservationInfosByDate() throws Exception {

        //given
        ReservationDto.DateRequestDto dateRequestDto = ReservationDto.DateRequestDto.builder()
                .reservationType(ReservationType.REQUEST)
                .selectDate(LocalDate.of(2019, 3, 24))
                .build();

        //when
        List<ReservationDto.GetDto> getDtos = reservationService.getReservationInfosByDate(dateRequestDto);

        //then
        getDtos.stream().forEach(s -> System.out.println(s.toString()));
    }

    @Test
    public void getReservationInfosByDateRange() throws Exception {

        //given
        ReservationDto.DateRangeRequestDto dateRangeRequestDto = ReservationDto.DateRangeRequestDto.builder()
                .reservationType(ReservationType.REQUEST)
                .startDate(LocalDate.of(2019, 3, 24))
                .endDate(LocalDate.of(2019, 3, 30))
                .build();

        //when
        List<ReservationDto.GetDto> getDtos = reservationService.getReservationInfosByDateRange(dateRangeRequestDto);

        //then
        getDtos.stream().forEach(s -> System.out.println(s.toString()));
    }

}