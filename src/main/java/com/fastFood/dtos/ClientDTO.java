package com.fastFood.dtos;

import com.fastFood.clientes.TypeClient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ClientDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull BigDecimal balance,
        @NotBlank String email,
        @NotNull TypeClient typeAccount) {
}
