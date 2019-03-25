package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.BreadDto;
import com.dev.olive.olivebakery.domain.enums.DayType;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.domain.entity.Bread;
import com.dev.olive.olivebakery.repository.BreadRepository;
import com.dev.olive.olivebakery.repository.SoldOutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by YoungMan on 2019-02-12.
 */

@Service
public class BreadService {

    private final BreadRepository breadRepository;
    private final SoldOutRepository soldOutRepository;

    public BreadService(BreadRepository breadRepository, SoldOutRepository soldOutRepository) {
        this.breadRepository = breadRepository;
        this.soldOutRepository = soldOutRepository;
    }

    public Bread findByName(String breadName) {
        return breadRepository.findByName(breadName)
                .orElseThrow(() -> new UserDefineException("해당 이름의 빵이 없습니다."));
    }

    public List<Bread> findsByNames(List<String> breadNames) {
        return breadRepository.findByNameIn(breadNames);
    }

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

    public List<BreadDto.GetAll> getBreadByDay(DayType day){
        List<Bread> breadList = breadRepository.findByDays(day);
        List<BreadDto.GetAll> breadGetAll = new ArrayList<>();
        breadList.forEach(bread -> {
            boolean isSoldOut = false;
            if(bread.getSoldOut() != null)
                isSoldOut = bread.getSoldOut().getDate().isEqual(LocalDate.now());
            breadGetAll.add(
                BreadDto.GetAll.builder()
                        .picturePath(bread.getPicturePath())
                        .name(bread.getName())
                        .price(bread.getPrice())
                        .description(bread.getDescription())
                        .soldOut(isSoldOut)
                        .breadState(bread.getState())
                        .build());

        });

        return breadGetAll;
    }

    public BreadDto.GetDetail getBreadDetails(Long breadId){
        Bread bread = breadRepository.findById(breadId)
                .orElseThrow(() -> new UserDefineException("빵의 Id를 잘못 입력하셨습니다."));
        boolean isSoldOut = false;
        if(bread.getSoldOut() != null)
            isSoldOut = bread.getSoldOut().getDate().isEqual(LocalDate.now());

        List<BreadDto.Ingredient> ingredientList = new ArrayList<>();
        bread.getIngredients().forEach(ingredient -> ingredientList.add(
                BreadDto.Ingredient.builder()
                        .ingredient(ingredient.getName())
                        .origin(ingredient.getOrigin())
                        .build()
        ));

        return BreadDto.GetDetail.builder()
                .name(bread.getName())
                .price(bread.getPrice())
                .picturePath(bread.getPicturePath())
                .detailDescription(bread.getDetailDescription())
                .ingredientsList(ingredientList)
                .soldOut(isSoldOut)
                .build();
    }
}
