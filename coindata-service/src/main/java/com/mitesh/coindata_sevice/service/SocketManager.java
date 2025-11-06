package com.mitesh.coindata_sevice.service;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SocketManager {

    private final ConcurrentHashMap<String, WebSocket> openSockets = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    SocketManager(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate=messagingTemplate;
    }

    public void openSocket(String id){
        if(openSockets.containsKey(id)){
            System.out.println("already open socket for "+id);
            return;
        }
        String url = "wss://stream.binance.com:9443/ws/" + id + "@trade";
        System.out.println("open socket for "+url);
        //code to open the socket
        System.out.println("Connecting to Binance for " + id);
        //creating a new binance websocket
        HttpClient.newHttpClient().newWebSocketBuilder()
                .buildAsync(URI.create(url), new BinanceWebSocket(id, messagingTemplate))
                .thenAccept(ws -> openSockets.put(id, ws));
    }
    public void closeAllSocket(){
        for(String key: openSockets.keySet()){
            WebSocket socket = openSockets.get(key);
            socket.sendClose(WebSocket.NORMAL_CLOSURE, "Closing connection").join();
            System.out.println("closing socket for "+key);
        }
    }
}
