package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
