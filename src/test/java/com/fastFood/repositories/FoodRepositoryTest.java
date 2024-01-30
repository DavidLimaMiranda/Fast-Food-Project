package com.fastFood.repositories;

import com.fastFood.clientes.Client;
import com.fastFood.clientes.TypeClient;
import com.fastFood.dtos.ClientDTO;
import com.fastFood.dtos.FoodDTO;
import com.fastFood.food.Food;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class FoodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Caso de sucesso ao procurar o lanche pelo nome")
    void findFoodtByNameFoodCaseSucesse() {

       var foodDTO = new FoodDTO(
               "Hamburguer",
               "Delicioso",
               3,
               new BigDecimal(15));
       this.createFood(foodDTO);

       Optional<Food> result = this.foodRepository.findFoodByfoodName("Hamburguer");

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Caso de fracasso ao procurar o lanche pelo nome")
    void findFoodtByNameFoodCaseFail() {

        Optional<Food> result = this.foodRepository.findFoodByfoodName("Hamburguer");

        assertThat(result.isEmpty()).isTrue();
    }


    private Food createFood(FoodDTO food) {
        var newFood = new Food(food);

        this.entityManager.persist(newFood);
        return newFood;
    }


}
