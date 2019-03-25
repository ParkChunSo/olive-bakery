package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.entity.*;
import com.dev.olive.olivebakery.domain.enums.DayType;
import com.dev.olive.olivebakery.repository.BreadRepository;
import com.dev.olive.olivebakery.repository.DaysRepository;
import com.dev.olive.olivebakery.repository.MemberRepository;
import com.dev.olive.olivebakery.repository.ReviewRepository;
import com.dev.olive.olivebakery.service.BreadService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BreadControllerTest {
    @Autowired
    DaysRepository daysRepository;

    @Autowired
    BreadRepository breadRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MemberRepository memberRepository;

    MockMvc mockMvc;

    @Autowired
    BreadService breadService;

    @Before
    public void setup(){
        // 이곳에서 BreadController MockMvc 객체로 만듭니다.
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BreadController(breadService)).build();
    }

    @Test
    public void getDay(){
        Ingredient ingredient1 = Ingredient.builder().name("밀가루").origin("중국").build();
        Ingredient ingredient2 = Ingredient.builder().name("버터").origin("한국").build();
        Ingredient ingredient3 = Ingredient.builder().name("땅콩").origin("미국").build();
        Ingredient ingredient4 = Ingredient.builder().name("호두").origin("한국").build();
        Ingredient ingredient5 = Ingredient.builder().name("버터").origin("한국").build();
        Ingredient ingredient6 = Ingredient.builder().name("땅콩").origin("미국").build();

        List<Ingredient> ingredients1 = new ArrayList<>();
        List<Ingredient> ingredients2 = new ArrayList<>();

        ingredients1.add(ingredient1);
        ingredients1.add(ingredient2);
        ingredients1.add(ingredient3);

        ingredients2.add(ingredient5);
        ingredients2.add(ingredient6);
        ingredients2.add(ingredient4);

        Bread bread1 = Bread.builder()
                .description("맛있는 빵")
                .name("치아바타")
                .picturePath("asdfasdf")
                .price(20000)
                .ingredients(ingredients1)
                .detailDescription("자세한 설명입니다.111111")
                .star(1).build();

        Bread bread2 = Bread.builder()
                .description("맛있는 빵222")
                .name("치아바타22")
                .picturePath("asdfasdf")
                .price(20000)
                .ingredients(ingredients2)
                .detailDescription("자세한 설명입니다.222222")
                .star(1).build();

        Days days1 = Days.builder()
                .bread(bread1)
                .day(DayType.MON).build();
        Days days2 = Days.builder()
                .bread(bread1)
                .day(DayType.WEN).build();
        Days days3 = Days.builder()
                .bread(bread1)
                .day(DayType.FRI).build();

        Days days4 = Days.builder()
                .bread(bread2)
                .day(DayType.MON).build();
        Days days5 = Days.builder()
                .bread(bread2)
                .day(DayType.TUE).build();
        Days days6 = Days.builder()
                .bread(bread2)
                .day(DayType.THU).build();


        Member user = Member.builder()
                .email("qwdbstkd@naver.com")
                .name("김윤상")
                .phoneNumber("123-123-123")
                .pw("111")
                .stamp(111).build();

        Date date = new Date();
        Review review1 = Review.builder()
                .bread(bread1)
                .content("정말 맛있어요")
                .date(date)
                .member(user).build();

        Review review2 = Review.builder()
                .bread(bread1)
                .content("정말 맛있어요22222")
                .date(date)
                .member(user).build();

        breadRepository.save(bread1);
        breadRepository.save(bread2);

        daysRepository.save(days1);
        daysRepository.save(days2);
        daysRepository.save(days3);
        daysRepository.save(days4);
        daysRepository.save(days5);
        daysRepository.save(days6);

        memberRepository.save(user);

        reviewRepository.save(review1);
        reviewRepository.save(review2);



        //List<Days> days = daysRepository.findByDay("월");

        //assertThat(days.size()).isEqualTo(1);
        //assertThat(days.get(0).getBread().getName()).isEqualTo("치아바타");

        //assertThat(daysRepository.findBread(DayType.MON).size()).isEqualTo(1);
        //assertThat(daysRepository.findBread("월").get(0).getName()).isEqualTo("치아바타");


//        assertThat(reviewRepository.findByBread(bread1).size()).isEqualTo(2);
    }

    @Test
    public void getBread() throws Exception{
        this.mockMvc.perform(get("localhost:8080/olive/bread/day/{day}", "mon")).andDo(print())
                .andExpect(status().isOk());

//        assertThat(daysRepository.findBread(DayType.MON).size()).isEqualTo(2);
        //daysRepository.findBread(DayType.valueOf("MON"));
    }

    @Test
    public void saveBread() throws Exception{


    }
}