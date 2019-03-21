package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients , Long> {
}
