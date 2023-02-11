package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.TransactionModel;
import com.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionModel> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionModel getTransactionById(@PathVariable(value = "id") Long transactionId)
            throws ResourceNotFoundException {
        return transactionService.getTransactionById(transactionId);
    }
//    @GetMapping("/customers/{idOrName}/rewards")
//    public CustomerModel getCustomerRewards(@PathVariable String idOrName) {
//        try {
//            return transactionService.getCustomer(idOrName);
//        } catch (CustomerNotFoundException e) {
//            throw e;
//        }
//    }

    @PostMapping
    public TransactionModel createTransaction(@Valid @RequestBody TransactionModel transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public TransactionModel updateTransaction(@PathVariable(value = "id") Long transactionId,
                                         @Valid @RequestBody TransactionModel transactionDetails) throws ResourceNotFoundException {
        return transactionService.updateTransaction(transactionId, transactionDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable(value = "id") Long transactionId) throws ResourceNotFoundException {
        transactionService.deleteTransaction(transactionId);
    }
}

