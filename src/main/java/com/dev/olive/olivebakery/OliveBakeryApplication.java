package com.dev.olive.olivebakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OliveBakeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OliveBakeryApplication.class, args);
    }

}

