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


}