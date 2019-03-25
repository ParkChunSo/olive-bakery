package com.dev.olive.olivebakery.domain.entity;

import com.dev.olive.olivebakery.domain.enums.BreadState;
import com.dev.olive.olivebakery.domain.enums.DayType;
import com.dev.olive.olivebakery.repository.BreadRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BreadTest {
    @Autowired
    BreadRepository breadRepository;

    @Test
    public void insert(){
        List<Bread> breadList = new ArrayList<>();


        for(int i = 0 ; i< 10 ; i ++){
            Bread bread = Bread.builder()
                                .name(i + "번째 빵")
                                .description(i + "번째 빵은 맛있습니다.")
                                .detailDescription(i + "번째 빵은 정말 맛있습니다.")
                                .picturePath("/home/pictures/bread" + i)
                                .days(Stream.of(DayType.THU, DayType.WEN).collect(Collectors.toSet()))
                                .price(3000)
                                .state(BreadState.NORMAL)
                                .build();
            List<Ingredients> ingredientsList = new ArrayList<>();
            Ingredients ingredients = new Ingredients();
            ingredients.setName("밀가루");
            ingredients.setOrigin("한국");
            ingredients.setBread(bread);
            Ingredients ingredients2 = new Ingredients();
            ingredients2.setName("박력분");
            ingredients2.setOrigin("한국");
            ingredients2.setBread(bread);
            ingredientsList.add(ingredients); ingredientsList.add(ingredients2);
            bread.setIngredients(ingredientsList);

            breadList.add(bread);
        }

        breadRepository.saveAll(breadList);
    }
}