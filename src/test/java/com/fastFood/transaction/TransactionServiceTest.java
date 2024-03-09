package com.fastFood.transaction;

import com.fastFood.clientes.Client;
import com.fastFood.clientes.TypeClient;
import com.fastFood.dtos.TransactionDTO;
import com.fastFood.food.Food;
import com.fastFood.repositories.TransactionRepository;
import com.fastFood.services.entities.ClientService;
import com.fastFood.services.entities.FoodService;
import com.fastFood.services.entities.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class TransactionServiceTest {

    @Mock
    private ClientService clientService;

    @Mock
    private FoodService foodService;

    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Caso de suceeso com tudo funcionando corretamente")
    void createTransactionCaseSucesse() throws Exception {

        var company = new Client(1l, "company", "empresa", "company@gmail.com", "1234", new BigDecimal(100), TypeClient.COMPANY);
        var client = new Client(2l, "David", "Lima", "davidTeste@gmail.com", "1234", new BigDecimal(20), TypeClient.CLIENT);

        var lanche = new Food(1l, "Hamburguer", "Delicioso", 3, new BigDecimal(15));

        when(clientService.findClientById(2l)).thenReturn(client);
        when(clientService.findClientById(1l)).thenReturn(company);

        when(foodService.findFoodByName("Hamburguer")).thenReturn(lanche);
        when(foodService.verificationFoods("Hamburguer")).thenReturn(new BigDecimal(15));

        var transaction = new TransactionDTO(1l, 2l, "Hamburguer");
        transactionService.createTransaction(transaction);

        verify(transactionRepository, times(1)).save(any());

        client.setBalance(new BigDecimal(5));
        verify(clientService, times(1)).saveClient(client);

        company.setBalance(new BigDecimal(115));
        verify(clientService, times(1)).saveClient(company);
    }

    @Test
    @DisplayName("Caso de fracasso da requisição por conflito nos valores")
    void createTransactionCaseFailValue() throws Exception {

        var company = new Client(1l, "company", "empresa", "company@gmail.com", "12345",new BigDecimal(100), TypeClient.COMPANY);
        var client = new Client(2l, "David", "Lima", "davidTeste@gmail.com", "12345", new BigDecimal(10), TypeClient.CLIENT);

        var lanche = new Food(1l, "Hamburguer", "Delicioso", 3, new BigDecimal(15));

        when(clientService.findClientById(2l)).thenReturn(client);
        when(clientService.findClientById(1l)).thenReturn(company);

        when(foodService.findFoodByName("Hamburguer")).thenReturn(lanche);
        when(foodService.verificationFoods("Hamburguer")).thenReturn(new BigDecimal(15));

        Exception thrown = assertThrows(Exception.class, () -> {

            when(transactionService.validateTransaction(client, lanche.getPrice())).thenReturn(false);

        });

       assertEquals("Você não possui essa quantia em seu saldo no momento para realizar essa compra.", thrown.getMessage());
    }

    @Test
    @DisplayName("Caso de fracasso da requisição por conflito no tipo do cliente")
    void createTransactionCaseFailTypeClient() throws Exception {

        var company = new Client(1l, "company", "empresa", "company@gmail.com", "123456",new BigDecimal(100), TypeClient.COMPANY);
        var client = new Client(2l, "David", "Lima", "davidTeste@gmail.com", "123456",new BigDecimal(20), TypeClient.CLIENT);

        var lanche = new Food(1l, "Hamburguer", "Delicioso", 3, new BigDecimal(15));

        when(clientService.findClientById(2l)).thenReturn(client);
        when(clientService.findClientById(1l)).thenReturn(company);

        when(foodService.findFoodByName("Hamburguer")).thenReturn(lanche);
        when(foodService.verificationFoods("Hamburguer")).thenReturn(new BigDecimal(15));

        Exception thrown = assertThrows(Exception.class, () -> {

            when(transactionService.validateTransaction(company, lanche.getPrice())).thenReturn(false);

        });

        assertEquals("A empresa não pode realizar transações para um cliente", thrown.getMessage());
    }
}
