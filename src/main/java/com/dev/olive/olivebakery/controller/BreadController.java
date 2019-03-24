package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.dto.ReviewDto;
import com.dev.olive.olivebakery.service.BreadService;
import com.dev.olive.olivebakery.service.ShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 * 1. 빵 하나씩 가져오기
 * 2. 빵 저장
 * 3. 빵 정보 추가.
 * 4. 리스트로 보여줄 때는 description이랑 가격만.
 * 5.
 */
@RestController
@RequestMapping(value = "/olive/bread")
@Log4j2
public class BreadController {

    ShoppingService shoppingService;
    private final BreadService breadService;

    public BreadController(BreadService breadService, ShoppingService shoppingService){
        this.shoppingService = shoppingService;
        this.breadService = breadService;
    }

    @ApiOperation("빵 전체 정보 가져오기")
    @GetMapping
    public List<BreadDto> getBread(){
        return breadService.getBread();
    }

    @GetMapping("/review/{bread}")
    public List<ReviewDto> getReview(@PathVariable String bread){
        return shoppingService.getReview(bread);
    }
}
