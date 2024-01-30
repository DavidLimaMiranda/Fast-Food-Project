package com.fastFood.services;

import com.fastFood.dtos.FoodDTO;
import com.fastFood.repositories.FoodRepository;
import com.fastFood.food.Food;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food createFood(FoodDTO foodDTO) throws Exception {

        Optional<Food> verificationFood = this.foodRepository.findFoodByfoodName(foodDTO.foodName());

        if(verificationFood.isPresent())
        {
            throw new Exception("Lanche já cadastrado no cardapio.");
        }

        var food = new Food(foodDTO);

        this.saveFood(food);

        return food;
    }

    public void saveFood(Food food) {

        this.foodRepository.save(food);
    }

    public List<Food> listAllFoods() {

        return this.foodRepository.findAll();
    }

    public Food findFood(Long id) throws Exception {

        return this.foodRepository.findFoodById(id).orElseThrow(() -> new Exception("Lanche não encontrado"));
    }

    public Food findFoodByName(String name) throws Exception {

        return this.foodRepository.findFoodByfoodName(name).orElseThrow(() -> new Exception(String.format("Lanche %s não encontrado", name)));
    }

    public BigDecimal verificationFoods(String foods) throws Exception {

        String[] splitFoods = foods.split(",");

        BigDecimal value = BigDecimal.ZERO;

        for(String product: splitFoods)
        {
           var food = this.findFoodByName(product.trim());

           this.verificationStockFood(food);

           food.setStock(food.getStock() - 1);

           this.saveFood(food);

           value = value.add(food.getPrice());
        }

        return value;
    }

    public boolean verificationStockFood(Food food) throws Exception {

        if(food.getStock() <= 0)
        {
            throw new Exception("Lanche fora de stock");
        }

        return true;
    }
}
