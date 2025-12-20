package com.mitesh.portfolio_service.service;

import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService {


    public String buy(String coinid){
        /*here we need to fetch the coindetails from coindata-service
        * and create a transaction.
        * that transaction will occur at the dashboard
        * closing can be done by clicking at that transaction*/
        return "bought "+coinid;
    }

    public void sell(String coinid) {
        /*here we need to fetch the coindetails from coindata-service
         * and create a transaction.
         * that transaction will occur at the dashboard
         * closing can be done by clicking at that transaction*/
        //return "sold "+coinid;
    }
}
