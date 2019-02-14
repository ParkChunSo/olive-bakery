package com.dev.olive.olivebakery.repository;


import com.dev.olive.olivebakery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}