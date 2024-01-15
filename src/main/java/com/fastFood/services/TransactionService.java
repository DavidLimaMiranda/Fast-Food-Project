package com.fastFood.services;

import com.fastFood.clientes.Client;
import com.fastFood.clientes.TypeClient;
import com.fastFood.dtos.TransactionDTO;
import com.fastFood.food.Food;
import com.fastFood.repositories.TransactionRepository;
import com.fastFood.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {

        return this.transactionRepository.findAll();
    }

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {

        Client client = this.clientService.findClientById(transaction.clientId());
        Client company = this.clientService.findClientById(transaction.companyId());

        BigDecimal value = this.foodService.verificationFoods(transaction.foods());

        var newTransaction = new Transaction();

        this.validateTransaction(client, value);

        newTransaction.setValue(value);
        newTransaction.setCompany(company);
        newTransaction.setClient(client);

        newTransaction.setFoods(transaction.foods());
        newTransaction.setTimestamp(LocalDateTime.now());

        client.setBalance(client.getBalance().subtract(value));
        company.setBalance(company.getBalance().add(value));

        this.transactionRepository.save(newTransaction);
        this.clientService.saveClient(client);
        this.clientService.saveClient(company);

        return newTransaction;
    }

    public void validateTransaction(Client client, BigDecimal value) throws Exception {

        if(client.getTypeAccount() == TypeClient.COMPANY)
        {
            throw new Exception("A empresa não pode realizar transações para um cliente");
        }

        if(client.getBalance().compareTo(value) < 0)
        {
            throw new Exception("Você não possui essa quantia em seu saldo no momento para realizar essa compra.");
        }
    }


}
