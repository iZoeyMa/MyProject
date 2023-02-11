package com.service;

import com.model.CustomerModel;
import com.model.TransactionModel;
import com.repository.TransactionRepository;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerService customerService;

    public List<TransactionModel> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public TransactionModel getTransactionById(long id) {
        return transactionRepository.findById(id);
    }
    public List<TransactionModel> getAllTransactionById(Long customerId) {
            List<TransactionModel> transactions = transactionRepository.findByCustomerId(customerId);
            return transactions;

    }


    public TransactionModel addTransaction(TransactionModel transaction) {
        transaction.setDateTime(new Date());
        return transactionRepository.save(transaction);
    }

    public TransactionModel updateTransaction(long id, TransactionModel transactionDetails) {
        TransactionModel transaction = getTransactionById(id);
        transaction.setAmount(transactionDetails.getAmount());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(long id) {
        transactionRepository.delete(getTransactionById(id));
    }

//    public long calculateRewards(long customerId) {
//        CustomerModel customer = customerService.getCustomerById(customerId);
//        long rewards = 0;
//        List<TransactionModel> transactions = customer.getTransactions();
//        for (TransactionModel transaction : transactions) {
//            long transactionAmount = transaction.getAmount();
//            if (transactionAmount > 100) {
//                rewards += 2 * (transactionAmount - 100) + 50;
//            } else if (transactionAmount > 50) {
//                rewards += 1 * (transactionAmount - 50);
//            }
//        }
//        return rewards;
//    }
}

