package com.fastFood.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FoodDTO(
        @NotBlank String foodName,
        @NotBlank String description,
        @NotNull int stock,
        @NotNull BigDecimal price) {
}
