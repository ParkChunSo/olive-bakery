package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService service;


    @Test
    public void findById() {
    }

    @Test
    public void saveReservation() {
    }

    @Test
    public void updateReservationType() {
    }

    @Test
    public void getReservationInfoByUserId() {
        service.getReservationInfoByUserId("cnsth");
    }
}