package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.ReservationDto;
import com.dev.olive.olivebakery.domain.entity.Bread;
import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.domain.entity.Reservation;
import com.dev.olive.olivebakery.domain.entity.ReservationInfo;
import com.dev.olive.olivebakery.domain.enums.ReservationType;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.repository.ReservationInfoRepository;
import com.dev.olive.olivebakery.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // 예약 정보 저장
    public void saveReservation(ReservationDto.SaveDto saveDto) {
        reservationInfoRepository.saveAll(convertSaveDtoToEntity(saveDto));
    }

    private List<ReservationInfo> convertSaveDtoToEntity(ReservationDto.SaveDto saveDto) {

        List<ReservationInfo> reservationInfos = new ArrayList<>();
        Member member = signService.findById(saveDto.getUserEmail());
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

    // 예약 정보 수정
    // TODO - 검토필요
    public void updateReservation(ReservationDto.UpdateDto updateDto) {

        deleteReservation(updateDto.getReservationId());
        saveReservation(updateDto.getSaveDto());
    }

    // 예약 정보 삭제
    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    // 예약 상태 수정
    public void updateReservationType(Long reservationId) {
        Reservation reservation = findById(reservationId);
        reservation.updateReservationType();
        reservationRepository.save(reservation);
    }

    // UserId 와 ReservationType 으로 예약 조회
    public List<ReservationDto.GetDto> getReservationInfoByUserId(String email, ReservationType reservationType) {

        List<Object[]> getTmpDtoObjects = reservationRepository.getReservationInfoByUserId(email, reservationType);
        List<ReservationDto.GetTmpDto> getTmpDtos = getTmpDtoObjects.stream()
                .map(testDto -> new ReservationDto.GetTmpDto(
                        (Long) testDto[0], (Timestamp) testDto[1], (Timestamp) testDto[2], (int) testDto[3], (String) testDto[4], (String) testDto[5], (int) testDto[6]
                )).collect(Collectors.toList());

        return convertGetTmpDtoToGetDto(getTmpDtos);
    }

    private List<ReservationDto.GetDto> convertGetTmpDtoToGetDto(List<ReservationDto.GetTmpDto> getTmpDtos) {

        List<ReservationDto.GetDto> getDtos = new ArrayList<>();

        Long reservationId = getTmpDtos.get(0).getReservationId();
        Timestamp reservationTime = getTmpDtos.get(0).getReservationTime();
        Timestamp bringTime = getTmpDtos.get(0).getBringTime();
        int price = getTmpDtos.get(0).getPrice();
        String memberName = getTmpDtos.get(0).getMemberName();
        List<ReservationDto.ReservationBread> reservationBreads = new ArrayList<>();

        for (ReservationDto.GetTmpDto getTmpDto : getTmpDtos) {
            if (reservationId.equals(getTmpDto.getReservationId())) {
                reservationBreads.add(
                        ReservationDto.ReservationBread.builder()
                                .breadName(getTmpDto.getBreadName())
                                .breadCount(getTmpDto.getBreadCount())
                                .build()
                );
            } else {
                getDtos.add(
                        ReservationDto.GetDto.builder()
                                .reservationId(reservationId)
                                .reservationTime(reservationTime)
                                .bringTime(bringTime)
                                .price(price)
                                .memberName(memberName)
                                .reservationBreads(reservationBreads)
                                .build()
                );

                reservationBreads = new ArrayList<>();
                reservationId = getTmpDto.getReservationId();
                reservationTime = getTmpDto.getReservationTime();
                bringTime = getTmpDto.getBringTime();
                price = getTmpDto.getPrice();
                memberName = getTmpDto.getMemberName();
                reservationBreads.add(
                        ReservationDto.ReservationBread.builder()
                                .breadName(getTmpDto.getBreadName())
                                .breadCount(getTmpDto.getBreadCount())
                                .build()
                );
            }
        }

        getDtos.add(
                ReservationDto.GetDto.builder()
                        .reservationId(reservationId)
                        .reservationTime(reservationTime)
                        .bringTime(bringTime)
                        .price(price)
                        .memberName(memberName)
                        .reservationBreads(reservationBreads)
                        .build()
        );
        return getDtos;
    }

    // 가장 최근에 예약한 1개 예약내역 가져오기.
    // TODO 상태랑 상관없이?
    public void getReservationInfo(String userId) {

    }

    // 현재 날짜로 예약된 예약정보 모두 가져오기
    // ReservaionType의 따라서 따로 보내줄것.(정렬도 괜춘) (Admin에서 사용)
    public void getReservaionInfoByDate(ReservationType reservationType) {

    }

    // 수령 시간 정보 확인하는 메소드
    // 수령시간은 매일 저녁 8시 이전이여야 하며 예약시간보다 늦을 수는 없다.
    // 잘못된 수령 시간은 무조건 빠꾸시킴.
    // TODO 프런트에서 처리하는게 어떤지
    public void validationCheck(Timestamp bringTime) {

    }
}
