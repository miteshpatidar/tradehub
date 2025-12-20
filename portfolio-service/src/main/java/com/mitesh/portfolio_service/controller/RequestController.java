package com.mitesh.portfolio_service.controller;

import com.mitesh.portfolio_service.model.User;
import com.mitesh.portfolio_service.service.AccountService;
import com.mitesh.portfolio_service.service.TransactionService;
import com.mitesh.portfolio_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class RequestController {

    /*We need to manage the duplications for followed coins
    * use set with list as required */
    /*we have to be synchronised so that multiple trades could be executed*/
    /*next thing to do is to implement the websocket connection with another service
    * than implement the services to act according the live data and perform transaction safely*/

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
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
    public void buy(@RequestParam String coinid){
        /*this will create a transaction and not close the existing
        * sold trasnsaction of same coinid
        * if i have to close the transaction than i need the transaction id
        * for that case there is another request of closetrancation*/
        transactionService.buy(coinid);
        //return the bought price or transaction id;
    }
    @PostMapping("sell")
    public void sell(@RequestParam String coinid){
        /*this will create a transaction and not close the existing
         * bought trasnsaction of same coinid
         * if i have to close the transaction than i need the transaction id
         * for that case there is another request of closetrancation*/
        transactionService.sell(coinid);
        //return the sold price;
    }
    @GetMapping("getbalance")
    public void getBalance(@RequestParam String userid){
        /*to get the current balance in useraccount
        * but this has to be in real time so we might need websockets
        * for this case*/
        //return balance
    }
    @GetMapping("gethistory")
    public void getHistory(@RequestParam String userid,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        //get the time range and return the data accordingly
        accountService.getHistory(userid,startDate,endDate);
    }
    @GetMapping("activetransaction")
    public void getActiveTransaction(@RequestParam String userid){
        //return active transaction
        /*here also if we show the transaction live data than
        * we will need the websockets, but if here we avoid than
        * for individual transaction has to be implemented using websockets*/

        /*Note:- the transactions data fetched from the db is not livestreamed data
        * means that the current value is to be calculated by fetching the live data from
        * another service and calculate the profit/loss with active transaction data from db
        * so, it has to be done at fronted or at another way*/
        accountService.getActiveTransactions();

    }
    @GetMapping("gettransaction")
    public void getTransaction(@RequestParam String userid,@RequestParam String transactionid){
        /*this gets triggered when a user clicks on a transaction*/
        accountService.getTransaction(userid,transactionid);
    }
    @PostMapping("closetransaction")
    public void closeTransaction(@RequestParam String userid,@RequestParam String transactionid){
        accountService.closeTransaction(userid,transactionid);
    }
}
