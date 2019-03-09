package com.dev.olive.olivebakery;

import com.dev.olive.olivebakery.entity.Bread;
import com.dev.olive.olivebakery.entity.Days;
import com.dev.olive.olivebakery.entity.Review;
import com.dev.olive.olivebakery.entity.User;
import com.dev.olive.olivebakery.repository.BreadRepository;
import com.dev.olive.olivebakery.repository.DaysRepository;
import com.dev.olive.olivebakery.repository.ReviewRepository;
import com.dev.olive.olivebakery.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyDataJpaTest {

    @Autowired
    DaysRepository daysRepository;

    @Autowired
    BreadRepository breadRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getDay(){
        Bread bread1 = Bread.builder().id(Long.valueOf(1))
                .description("맛있는 빵")
                .name("치아바타")
                .picturePath("asdfasdf")
                .price(20000)
                .soldOut(1)
                .star(1).build();

        Bread bread2 = Bread.builder().id(Long.valueOf(2))
                .description("맛있는 빵222")
                .name("치아바타22")
                .picturePath("asdfasdf")
                .price(20000)
                .soldOut(1)
                .star(1).build();

        Days days1 = Days.builder().id(Long.valueOf(1))
                .bread(bread1)
                .day("월").build();
        Days days2 = Days.builder().id(Long.valueOf(1))
                .bread(bread1)
                .day("수").build();
        Days days3 = Days.builder().id(Long.valueOf(1))
                .bread(bread1)
                .day("금").build();

        Days days4 = Days.builder().id(Long.valueOf(1))
                .bread(bread2)
                .day("화").build();
        Days days5 = Days.builder().id(Long.valueOf(1))
                .bread(bread2)
                .day("수").build();
        Days days6 = Days.builder().id(Long.valueOf(1))
                .bread(bread2)
                .day("목").build();

        User user = User.builder()
                .email("qwdbstkd@naver.com")
                .name("김윤상")
                .phoneNum("123-123-123")
                .pw("111")
                .stamp(111).build();

        Date date = new Date();
        Review review1 = Review.builder()
                .bread(bread1)
                .content("정말 맛있어요")
                .date(date)
                .user(user).build();

        Review review2 = Review.builder()
                .bread(bread1)
                .content("정말 맛있어요22222")
                .date(date)
                .user(user).build();

        breadRepository.save(bread1);
        breadRepository.save(bread2);

        daysRepository.save(days1);
        daysRepository.save(days2);
        daysRepository.save(days3);
        daysRepository.save(days4);
        daysRepository.save(days5);
        daysRepository.save(days6);

        reviewRepository.save(review1);
        reviewRepository.save(review2);

        userRepository.save(user);

        /*
        List<Days> days = daysRepository.findByDay("월");

        //assertThat(days.size()).isEqualTo(1);
        //assertThat(days.get(0).getBread().getName()).isEqualTo("치아바타");

        assertThat(daysRepository.findBread("월").size()).isEqualTo(1);
        assertThat(daysRepository.findBread("월").get(0).getName()).isEqualTo("치아바타");
        */

        assertThat(reviewRepository.findByBread(bread1).size()).isEqualTo(2);

    }
}
