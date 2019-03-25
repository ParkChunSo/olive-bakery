package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.enums.DayType;
import com.dev.olive.olivebakery.service.BreadService;
import com.dev.olive.olivebakery.service.ShoppingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/name/{name}")
    public BreadDto.GetDetail getDetail(@PathVariable String name){
        return breadService.getBreadDetails(name);
    }

    @ApiOperation("빵 정보 수정")
    @PutMapping
    public void updateBread(@RequestBody BreadDto.Save bread){

    }

    @ApiOperation("빵 저장")
    @PostMapping
    public void saveBread(@RequestBody BreadDto.Save bread){

    }

    @ApiOperation("빵 삭제")
    @DeleteMapping("/name/{name}")
    public void deleteBread(@PathVariable String name){

    }
}
