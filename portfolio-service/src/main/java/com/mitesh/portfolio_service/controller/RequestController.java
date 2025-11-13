package com.mitesh.portfolio_service.controller;

import com.mitesh.portfolio_service.model.User;
import com.mitesh.portfolio_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    /*We need to manage the duplications for followed coins
    * use set with list as required*/

    @Autowired
    private UserService userService;
    @PostMapping("newuser")
    public String createNewUser(@RequestBody User user){
        userService.createNewUser(user);
        return "Success";
    }

    @PostMapping("follow")
    public void followcoin(@RequestParam Integer userid, @RequestParam String coinid){
        userService.followCoin(userid,coinid);
    }
    @PostMapping("unfollow")
    public void unfollowcoin(@RequestParam Integer userid,@RequestParam String coinid){
        userService.unFollowCoin(userid,coinid);
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
