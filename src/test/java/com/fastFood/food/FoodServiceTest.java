package com.fastFood.food;

import com.fastFood.dtos.FoodDTO;
import com.fastFood.repositories.FoodRepository;
import com.fastFood.services.entities.FoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

public class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Autowired
    @InjectMocks
    private FoodService foodService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Caso de sucesso de lanche em stock")
    void verificandoSeLancheEstaEmStockSucsses() throws Exception {

        var foodDTO = new FoodDTO("Hamburguer", "Delicioso", 3, new BigDecimal(15));
        foodService.createFood(foodDTO);

        var lanche = new Food(foodDTO);

        when(foodRepository.findFoodByfoodName("Hamburguer")).thenReturn(Optional.of(lanche));
        when(foodService.verificationFoods(lanche.getFoodName())).thenReturn(new BigDecimal(2));

        assertEquals(lanche.getStock(), 2);
    }

    @Test
    @DisplayName("Caso de fracasso do lanche fora de stock")
    void verificandoSeLancheEstaEmStockFail() throws Exception {

        var foodDTO = new FoodDTO("Hamburguer", "Delicioso", 0, new BigDecimal(15));
        foodService.createFood(foodDTO);

        var lanche = new Food(foodDTO);

        when(foodRepository.findFoodByfoodName("Hamburguer")).thenReturn(Optional.of(lanche));

        Exception thrown = assertThrows(Exception.class, () -> {

           when(foodService.verificationFoods(lanche.getFoodName()));
        });

        assertEquals("Lanche fora de stock", thrown.getMessage());
    }

}
