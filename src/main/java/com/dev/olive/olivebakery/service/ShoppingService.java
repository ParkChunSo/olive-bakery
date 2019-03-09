package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.dto.BreadDto;
import com.dev.olive.olivebakery.dto.ReviewDto;
import com.dev.olive.olivebakery.entity.Bread;
import com.dev.olive.olivebakery.entity.Review;
import com.dev.olive.olivebakery.repository.BreadRepository;
import com.dev.olive.olivebakery.repository.DaysRepository;
import com.dev.olive.olivebakery.repository.ReviewRepository;
import com.dev.olive.olivebakery.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingService {
    private final UserRepository userRepository;
    private final BreadRepository breadRepository;
    private final ReviewRepository reviewRepository;
    private final DaysRepository daysRepository;

    public ShoppingService(UserRepository userRepository, BreadRepository breadRepository, ReviewRepository reviewRepository, DaysRepository daysRepository){
        this.userRepository = userRepository;
        this.breadRepository = breadRepository;
        this.reviewRepository = reviewRepository;
        this.daysRepository = daysRepository;
    }

    public List<BreadDto> getBreadByDay(String day){
        List<Bread> breads = daysRepository.findBread(day);

        List<BreadDto> breadDtos = new ArrayList<BreadDto>();
        for (Bread bread : breads){
            BreadDto breadDto = BreadDto.builder()
                    .name(bread.getName())
                    .description(bread.getDescription())
                    .picturePath(bread.getPicturePath())
                    .price(bread.getPrice())
                    .soldOut(bread.getSoldOut())
                    .star(bread.getStar()).build();
            breadDtos.add(breadDto);
        }

        return breadDtos;
    }

    public List<ReviewDto> getReview(String breadName){
        Bread bread = breadRepository.findByName(breadName);

        List<Review> reviews = reviewRepository.findByBread(bread);

        List<ReviewDto> reviewDtos = new ArrayList<ReviewDto>();

        for (Review review : reviews){
            ReviewDto reviewDto = ReviewDto.builder()
                    .userName(review.getUser().getName())
                    .content(review.getContent())
                    .date(review.getDate()).build();

            reviewDtos.add(reviewDto);
        }

        return reviewDtos;
    }
}