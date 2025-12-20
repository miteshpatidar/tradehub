package com.mitesh.portfolio_service.service;

import com.mitesh.portfolio_service.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    public List<Transaction> getHistory(String userid, LocalDate startDate, LocalDate endDate) {
        /*fetch the history based on time and return all the transaction*/
        return new ArrayList<>();
    }

    public void getActiveTransactions() {
        /*list all the active transactions*/
    }
}
