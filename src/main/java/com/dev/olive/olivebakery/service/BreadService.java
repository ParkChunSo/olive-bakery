package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.dto.ReviewDto;
import com.dev.olive.olivebakery.domain.entity.Days;
import com.dev.olive.olivebakery.domain.entity.Ingredient;
import com.dev.olive.olivebakery.domain.entity.Review;
import com.dev.olive.olivebakery.domain.enums.DayType;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.domain.entity.Bread;
import com.dev.olive.olivebakery.repository.BreadRepository;
import com.dev.olive.olivebakery.repository.DaysRepository;
import com.dev.olive.olivebakery.repository.ReviewRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by YoungMan on 2019-02-12.
 */

@Service
@Log4j2
public class BreadService {

    private final BreadRepository breadRepository;
    private final ReviewRepository reviewRepository;
    private final DaysRepository daysRepository;

    public BreadService(BreadRepository breadRepository, ReviewRepository reviewRepository, DaysRepository daysRepository) {
        this.breadRepository = breadRepository;
        this.reviewRepository = reviewRepository;
        this.daysRepository =daysRepository;
    }

    // 빵 이름으로 빵 하나 정보 가져오기
    public BreadDto findByName(String breadName) {
        Bread bread =  breadRepository.findByName(breadName)
                .orElseThrow(() -> new UserDefineException("해당 이름의 빵이 없습니다."));

        BreadDto breadDto = BreadDto.builder()
                .name(bread.getName())
                .detailDescription(bread.getDetailDescription())
                .picturePath(bread.getPicturePath())
                .price(bread.getPrice())
                .soldOut(bread.getIsSoldOut())
                .ingredients(bread.getIngredients())
                .star(bread.getStar()).build();

        return breadDto;
    }

    // 빵 이름으로 빵 리스트 주기
    public List<Bread> findsByNames(List<String> breadNames) {
        return breadRepository.findByNameIn(breadNames);
    }

    // 최종 금액 가져오기
    public int getFinalPrice(LinkedHashMap<String, Integer> breadInfos) {
        List<String> breadNames = new ArrayList<String>(breadInfos.keySet());
        List<Integer> counts = new ArrayList<Integer>(breadInfos.values());
        List<Bread> breads = findsByNames(breadNames);
        int finalPrice = 0;

        for (int i = 0; i < breadInfos.size(); i++) {
            finalPrice = finalPrice + (breads.get(i).getPrice() * counts.get(i));
        }

        return finalPrice;
    }

    /*public int getFinalPrice(List<Bread> breads) {
        return breads.stream().mapToInt(bread -> Math.toIntExact(bread.getPrice())).sum();
    }*/

    // 요일 별로 빵 가져오기
    public List<BreadDto> getBreadByDay(String day){

        log.info("=======" +day);
        List<Bread> breads = daysRepository.findBread(DayType.valueOf(day.toUpperCase()));
        log.info("==========getBreadByDay size : " + breads.size());
        List<BreadDto> breadDtos = getBreadList(breads);
        log.info("==========breadDtos size : " + breadDtos.size());
        return breadDtos;
    }

    // 빵 리뷰 가져오기
    public List<ReviewDto> getReview(String breadName){
        Bread bread = breadRepository.findByName(breadName).orElseThrow(() -> new UserDefineException("해당 빵은 없습니다."));

        List<Review> reviews = reviewRepository.findByBread(bread);

        List<ReviewDto> reviewDtos = new ArrayList<ReviewDto>();

        for (Review review : reviews){
            ReviewDto reviewDto = ReviewDto.builder()
                    .userName(review.getMember().getName())
                    .content(review.getContent())
                    .date(review.getDate()).build();

            reviewDtos.add(reviewDto);
        }

        return reviewDtos;
    }

    // 빵 엔티티에서 디티오 변환
    public List<BreadDto> convertBreadToBreadDto(List<Bread> breads){
        List<BreadDto> breadDtos = new ArrayList<>();
        for (Bread bread : breads){
            BreadDto breadDto = BreadDto.builder()
                    .name(bread.getName())
                    .description(bread.getDescription())
                    .picturePath(bread.getPicturePath())
                    .price(bread.getPrice())
                    .soldOut(bread.getIsSoldOut())
                    .star(bread.getStar()).build();
            breadDtos.add(breadDto);
        }
        return breadDtos;
    }

    // 빵 리스트 정보
    public List<BreadDto> getBreadList(List<Bread> breads){
        List<BreadDto> breadDtos = new ArrayList<>();
        for (Bread bread : breads){
            BreadDto breadDto = BreadDto.builder()
                    .name(bread.getName())
                    .description(bread.getDescription())
                    .picturePath(bread.getPicturePath())
                    .price(bread.getPrice())
                    .build();
            breadDtos.add(breadDto);
        }
        return breadDtos;
    }

    public String saveBread(BreadDto breadDto){

        Bread bread = Bread.builder()
                .name(breadDto.getName())
                .price(breadDto.getPrice())
                .picturePath(breadDto.getPicturePath())
                .description(breadDto.getDescription())
                .ingredients(breadDto.getIngredients())
                .build();

        List<Days> daysList = saveDays(bread, breadDto.getDays());

        breadRepository.save(bread);
        daysRepository.saveAll(daysList);

        return "success";

    }

    public List<Days> saveDays(Bread bread, List<DayType> dayTypes){
        List<Days> daysList = new ArrayList<>();

        for(DayType dayType : dayTypes){
            Days days = Days.builder()
                    .bread(bread)
                    .day(dayType).build();
            daysList.add(days);
        }

        return daysList;
    }
}