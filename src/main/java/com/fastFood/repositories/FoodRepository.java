package com.fastFood.repositories;

import com.fastFood.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findFoodById(Long id);

    Optional<Food> findFoodByfoodName(String nameFood);
}
