package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.dto.ReviewDto;
import com.dev.olive.olivebakery.service.BreadService;
import com.dev.olive.olivebakery.service.ShoppingService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/olive/bread")
@Log4j2
public class BreadController {

    private final BreadService breadService;

    public BreadController(BreadService breadService){
        this.breadService = breadService;
    }

    @GetMapping("/day/{day}")
    public List<BreadDto> getBread(@PathVariable String day){
        log.info("----------------bread/day" + day.toUpperCase());
        return breadService.getBreadByDay(day);
    }

    @GetMapping("/review/{bread}")
    public List<ReviewDto> getReview(@PathVariable String bread){
        return breadService.getReview(bread);
    }

    @GetMapping("/name/{name}")
    public BreadDto getBreadByName(@PathVariable String name){
        return breadService.findByName(name);
    }

    @GetMapping()
    public String test(){
        return "fffffffffffffffff";
    }

}
