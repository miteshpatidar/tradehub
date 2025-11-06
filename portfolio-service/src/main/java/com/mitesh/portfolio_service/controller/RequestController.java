package com.mitesh.portfolio_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @PostMapping("follow")
    public void followcoin(String coinid){

    }
    @PostMapping("unfollow")
    public void unfollowcoin(String coinid){

    }
    @PostMapping("buy")
    public void buy(String coinid){
        //return the bought price;
    }
    @PostMapping("sell")
    public void sell(String coinid){
        //return the sold price;
    }
    @GetMapping("getbalance")
    public void getBalance(String userid){
        //return balance
    }
    @GetMapping("gethistory")
    public void getHistory(String userid){
        //get the time range and return the data accordingly
    }
    @GetMapping("activetransaction")
    public void getActiveTransaction(String userid){
        //return active transaction
    }
}
