package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(@Param("email") String email);
}
