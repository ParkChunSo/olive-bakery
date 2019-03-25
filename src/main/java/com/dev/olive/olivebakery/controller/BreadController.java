package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.enums.DayType;
import com.dev.olive.olivebakery.service.BreadService;
import com.dev.olive.olivebakery.service.ShoppingService;
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

    @ApiOperation("요일별 빵 정보 가져오기")
    @GetMapping("/day/{day}")
    public List<BreadDto.GetAll> getBread(@PathVariable DayType day){
        return breadService.getBreadByDay(day);
    }

    @ApiOperation("빵 상세정보 가져오기")
    @GetMapping("/id/{breadId}")
    public BreadDto.GetDetail getDetail(@PathVariable String breadId){
        return breadService.getBreadDetails(Long.valueOf(breadId));
    }
}
