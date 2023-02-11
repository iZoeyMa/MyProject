package com.service;
import java.time.LocalDate;
import java.util.*;

import com.exception.CustomerException;
import com.model.CustomerModel;
import com.model.TransactionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {
    private final TransactionService transactionService;
    private final CustomerService customerService;

    public RewardsService(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    public int calculatePoints(TransactionModel transaction) {
        int points = 0;
        if (transaction.getAmount() > 100) {
            points += 2 * (transaction.getAmount() - 100);
        }
        if (transaction.getAmount() > 50) {
            points += 1 * (transaction.getAmount() - 50);
        }
        return points;
    }

    public Map<Integer, Integer> getCustomerRewardsByMonth(long customerId) {
        CustomerModel customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerException("Customer not found", HttpStatus.NOT_FOUND);
        }

        Map<Integer, Integer> rewardsByMonth = new HashMap<>();
        List<TransactionModel> transactions = transactionService.getAllTransactionById(customerId);
        for (TransactionModel transaction : transactions) {
            Date date = transaction.getDateTime();
            int month = date.getMonth();
            if (!rewardsByMonth.containsKey(month)) {
                rewardsByMonth.put(month, 0);
            }
            rewardsByMonth.put(month, rewardsByMonth.get(month) + calculatePoints(transaction));
        }
        return rewardsByMonth;
    }

    public int getCustomerTotalRewards(long customerId) {
        CustomerModel customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerException("Customer not found", HttpStatus.NOT_FOUND);
        }

        List<TransactionModel> transactions = transactionService.getAllTransactionById(customerId);
        int totalRewards = 0;
        for (TransactionModel transaction : transactions) {
            totalRewards += calculatePoints(transaction);
        }
        return totalRewards;
    }

    public Map<String, Integer> getAllCustomerRewardsByMonth() {
        Map<String, Integer> rewardsByMonth = new HashMap<>();
        List<CustomerModel> customers = customerService.getAllCustomers();
        for (CustomerModel customer : customers) {
            Map<Integer, Integer> customerRewardsByMonth = getCustomerRewardsByMonth(customer.getId());
            for (Map.Entry<Integer, Integer> entry : customerRewardsByMonth.entrySet()) {
                String month = String.valueOf(entry.getKey());
                int reward = entry.getValue();
                if (!rewardsByMonth.containsKey(month)) {
                    rewardsByMonth.put(month, 0);
                }
                rewardsByMonth.put(month, rewardsByMonth.get(month) + reward);
            }
        }
        return rewardsByMonth;
    }
}

