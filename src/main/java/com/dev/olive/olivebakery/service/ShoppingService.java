package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.dto.ReviewDto;
import com.dev.olive.olivebakery.domain.entity.Bread;
import com.dev.olive.olivebakery.domain.entity.Review;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.repository.BreadRepository;
import com.dev.olive.olivebakery.repository.DaysRepository;
import com.dev.olive.olivebakery.repository.ReviewRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingService {
    private final BreadRepository breadRepository;
    private final ReviewRepository reviewRepository;
    private final DaysRepository daysRepository;

    public ShoppingService(BreadRepository breadRepository, ReviewRepository reviewRepository, DaysRepository daysRepository){
        this.breadRepository = breadRepository;
        this.reviewRepository = reviewRepository;
        this.daysRepository = daysRepository;
    }

    public List<BreadDto> getBreadByDay(String day){
        List<Bread> breads = daysRepository.findBread(day);

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
}