package com.dev.olive.olivebakery.repository;


import com.dev.olive.olivebakery.entity.Bread;
import com.dev.olive.olivebakery.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBread(Bread bread);
}
