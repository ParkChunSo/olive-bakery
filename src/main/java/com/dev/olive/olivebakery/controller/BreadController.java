package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.dto.ReviewDto;
import com.dev.olive.olivebakery.service.BreadService;
import com.dev.olive.olivebakery.service.ShoppingService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/olive/bread")
@Log4j2
public class BreadController {

    private final BreadService breadService;

    public BreadController(BreadService breadService){
        this.breadService = breadService;
    }

    // 요일 별로 리스트로 가져오기
    @GetMapping("/day/{day}")
    public List<BreadDto> getBread(@PathVariable String day){
        log.info("----------------bread/day" + day.toUpperCase());
        return breadService.getBreadByDay(day);
    }

    //빵 리뷰
    @GetMapping("/review/{bread}")
    public List<ReviewDto> getReview(@PathVariable String bread){
        return breadService.getReview(bread);
    }

    // 빵 하나씩 가져오기
    @GetMapping("/name/{name}")
    public BreadDto getBreadByName(@PathVariable String name){
        return breadService.findByName(name);
    }

    // 빵 저장
    @PostMapping()
    public String saveBread(@RequestBody BreadDto breadDto){
        return breadService.saveBread(breadDto);
    }

    @GetMapping()
    public String test(){
        return "fffffffffffffffff";
    }

}
