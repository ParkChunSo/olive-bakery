package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.dto.ReviewDto;
import com.dev.olive.olivebakery.service.ShoppingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/olive")
@Log4j2
public class BreadController {

    ShoppingService shoppingService;

    public BreadController(ShoppingService shoppingService){
        this.shoppingService = shoppingService;
    }

    @GetMapping("/bread/{day}")
    public List<BreadDto> getBread(@PathVariable String day){
        return shoppingService.getBreadByDay(day);
    }

    @GetMapping("/review/{bread}")
    public List<ReviewDto> getReview(@PathVariable String bread){
        return shoppingService.getReview(bread);
    }
}