package com.mitesh.coindata_sevice.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BinanceWebSocket implements WebSocket.Listener {

    private final String id;
    private final SimpMessagingTemplate messagingTemplate;

    public BinanceWebSocket(String id, SimpMessagingTemplate messagingTemplate) {
        this.id = id;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        System.out.println("Connected to Binance for: " + id);
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        messagingTemplate.convertAndSend("/topic/" + id.toLowerCase(), data.toString());
        webSocket.request(1);
//        return null;
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        System.err.println("Error on " + id + " stream: " + error.getMessage());
    }
}
