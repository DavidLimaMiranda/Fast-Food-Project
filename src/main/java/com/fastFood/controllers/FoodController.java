package com.fastFood.controllers;

import com.fastFood.dtos.FoodDTO;
import com.fastFood.services.entities.FoodService;
import com.fastFood.food.Food;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> listAllFoods() {

        var foods = foodService.listAllFoods();

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> findFood(@PathVariable Long id) throws Exception {

        var food = this.foodService.findFood(id);

        return new ResponseEntity<>(food, HttpStatus.FOUND);
    }
    @PostMapping("/food")
    public ResponseEntity<Food> createNewFood(@RequestBody @Valid FoodDTO food) throws Exception {

        var newFood = this.foodService.createFood(food);

        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }
}
