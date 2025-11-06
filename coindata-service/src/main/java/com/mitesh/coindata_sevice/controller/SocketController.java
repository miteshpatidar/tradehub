package com.mitesh.coindata_sevice.controller;

import com.mitesh.coindata_sevice.service.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SocketController {

    @Autowired
    private SocketManager socketManager;

    @MessageMapping("/subscribecoins")
    public void subscribeCoins(List<String> coinids){
        //this open the socket if not
        for(String id:coinids){
            socketManager.openSocket(id);
        }
    }
    @MessageMapping("/unsubscribeall")
    public void unSubscribeAll(){
        //this open the socket if not
        System.out.println("called for unsubscribeall");
        socketManager.closeAllSocket();
    }

}
