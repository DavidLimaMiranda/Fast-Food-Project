package com.fastFood.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(
        @NotNull Long companyId,
        @NotNull Long clientId,
        @NotNull String foods) {
}
