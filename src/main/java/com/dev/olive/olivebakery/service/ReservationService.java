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
import com.dev.olive.olivebakery.utill.Explain;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YoungMan on 2019-02-09.
 */

@SuppressWarnings("Duplicates")
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

    @Explain("예약 정보 저장")
    public void saveReservation(ReservationDto.SaveDto saveDto) {
        reservationInfoRepository.saveAll(convertSaveDtoToEntity(saveDto));
    }

    @Explain("saveReservation 의 서브함수")
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


    // TODO - 검토필요
    @Explain("예약 정보 수정")
    public void updateReservation(ReservationDto.UpdateDto updateDto) {
        deleteReservation(updateDto.getReservationId());
        saveReservation(updateDto.getSaveDto());
    }

    @Explain("예약 정보 삭제")
    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Explain("예약 상태 수정")
    public void updateReservationType(Long reservationId) {
        Reservation reservation = findById(reservationId);
        reservation.updateReservationType();
        reservationRepository.save(reservation);
    }

    @Explain("유저의 모든 예약내역을 예약타입별로 가져옴 ")
    public List<ReservationDto.GetDto> getReservationInfos(String email, ReservationType reservationType) {

        List<ReservationDto.GetTmpDto> getTmpDtoObjects = reservationRepository.getReservationInfos(email, reservationType);
       /* List<ReservationDto.GetTmpDto> getTmpDtos = getTmpDtoObjects.stream()
                .map(testDto -> new ReservationDto.GetTmpDto(
                        (Long) testDto[0], (LocalDateTime) testDto[1], (LocalDateTime) testDto[2], (int) testDto[3], (String) testDto[4], (String) testDto[5], (int) testDto[6]
                )).collect(Collectors.toList());
*/
        return convertGetTmpDtosToGetDtos(getTmpDtoObjects);
    }

    @Explain("유저의 가장 최근 예약내역을 예약타입에 무관하게 조회")
    public ReservationDto.GetDto getReservationInfoByRecently(String email) {

       /* List<Object[]> getTmpDtoObjects = reservationRepository.get
        List<ReservationDto.GetTmpDto> getTmpDtos = getTmpDtoObjects.stream()
                .map(testDto -> new ReservationDto.GetTmpDto(
                        (Long) testDto[0], (LocalDateTime) testDto[1], (LocalDateTime) testDto[2], (int) testDto[3], (String) testDto[4], (String) testDto[5], (int) testDto[6]
                )).collect(Collectors.toList());

        return convertGetTmpDtoToGetDto(getTmpDtos);*/
       return null;
    }

    @Explain("날짜별 예약 조회, Admin 에서 사용")
    public List<ReservationDto.GetDto> getReservationInfosByDate(ReservationDto.DateRequestDto dateRequestDto) {

        LocalDateTime startDate = dateRequestDto.getSelectDate().atStartOfDay();
        LocalDateTime endDate = dateRequestDto.getSelectDate().atTime(23,59,59);

//        List<Object[]> getTmpDtoObjects = reservationRepository.getReservationInfosByDate(dateRequestDto.getReservationType(), startDate, endDate);
        List<ReservationDto.GetTmpDto> getTmpDtoObjects = reservationRepository.getReservationInfosByDate(dateRequestDto.getReservationType(), startDate, endDate);
        /*System.out.println(getTmpDtoObjects.size() + "-----------------------------------------");
        List<ReservationDto.GetTmpDto> getTmpDtos = getTmpDtoObjects.stream()
                .map(testDto -> new ReservationDto.GetTmpDto(
                        (Long) testDto[0], (LocalDateTime) testDto[1], (LocalDateTime) testDto[2], (int) testDto[3], (String) testDto[4], (String) testDto[5], (int) testDto[6]
                )).collect(Collectors.toList());*/

        return convertGetTmpDtosToGetDtos(getTmpDtoObjects);
    }

    @Explain("날짜구간별 예약 조회, Admin 에서 사용")
    public List<ReservationDto.GetDto> getReservationInfosByDateRange(ReservationDto.DateRangeRequestDto dateRangeRequestDto) {

        LocalDateTime startDate = dateRangeRequestDto.getStartDate().atStartOfDay();
        LocalDateTime endDate = dateRangeRequestDto.getEndDate().atTime(23,59,59);

//        List<Object[]> getTmpDtoObjects = reservationRepository.getReservationInfosByDate(dateRangeRequestDto.getReservationType(), startDate, endDate);
        List<ReservationDto.GetTmpDto> getTmpDtoObjects = reservationRepository.getReservationInfosByDate(dateRangeRequestDto.getReservationType(), startDate, endDate);
      /*  List<ReservationDto.GetTmpDto> getTmpDtos = getTmpDtoObjects.stream()
                .map(testDto -> new ReservationDto.GetTmpDto(
                        (Long) testDto[0], (LocalDateTime) testDto[1], (LocalDateTime) testDto[2], (int) testDto[3], (String) testDto[4], (String) testDto[5], (int) testDto[6]
                )).collect(Collectors.toList());*/
        return convertGetTmpDtosToGetDtos(getTmpDtoObjects);
    }

    // 수령 시간 정보 확인하는 메소드
    // 수령시간은 매일 저녁 8시 이전이여야 하며 예약시간보다 늦을 수는 없다.
    // 잘못된 수령 시간은 무조건 빠꾸시킴.
    // TODO 프런트에서 처리하는게 어떤지
    public void validationCheck(Timestamp bringTime) {

    }

    @Explain("GetTmpDto 를 GetDto 로 변환")
    private ReservationDto.GetDto convertGetTmpDtoToGetDto(List<ReservationDto.GetTmpDto> getTmpDtos) {

        ReservationDto.GetDto getDto = ReservationDto.GetDto.builder()
                .reservationId(getTmpDtos.get(0).getReservationId())
                .reservationTime(getTmpDtos.get(0).getReservationTime())
                .bringTime(getTmpDtos.get(0).getBringTime())
                .price(getTmpDtos.get(0).getPrice())
                .memberName(getTmpDtos.get(0).getMemberName())
                .build();

        List<ReservationDto.ReservationBread> reservationBreads = new ArrayList<>();

        for(ReservationDto.GetTmpDto getTmpDto : getTmpDtos) {
            reservationBreads.add(ReservationDto.ReservationBread.builder()
                    .breadName(getTmpDto.getBreadName())
                    .breadCount(getTmpDto.getBreadCount())
                    .build()
            );
        }
        getDto.setReservationBreads(reservationBreads);
        return getDto;
    }

    @Explain("GetTmpDtos 를 GetDtos 로 변환")
    private List<ReservationDto.GetDto> convertGetTmpDtosToGetDtos(List<ReservationDto.GetTmpDto> getTmpDtos) {

        List<ReservationDto.GetDto> getDtos = new ArrayList<>();

        Long reservationId = getTmpDtos.get(0).getReservationId();
        LocalDateTime reservationTime = getTmpDtos.get(0).getReservationTime();
        LocalDateTime bringTime = getTmpDtos.get(0).getBringTime();
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
}
