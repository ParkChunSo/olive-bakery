package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.model.entity.ReservationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-02-09.
 */

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {
}
