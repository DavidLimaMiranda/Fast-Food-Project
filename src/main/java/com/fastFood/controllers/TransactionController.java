package com.fastFood.controllers;

import com.fastFood.dtos.TransactionDTO;
import com.fastFood.services.entities.TransactionService;
import com.fastFood.transaction.Transaction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Transaction>> getAllTransactions() {

        List<Transaction> transactions = this.transactionService.getAllTransactions();

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid TransactionDTO transaction) throws Exception {

        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
