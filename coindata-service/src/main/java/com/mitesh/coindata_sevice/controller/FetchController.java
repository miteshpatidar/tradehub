package com.mitesh.coindata_sevice.controller;

import com.mitesh.coindata_sevice.configuration.WebClientConfig;
import com.mitesh.coindata_sevice.model.Coin;
import com.mitesh.coindata_sevice.service.DataService;
import com.mitesh.coindata_sevice.service.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("fetch")
public class FetchController {
//    all types of coins available
//    exchanges for particular coin
//    historical data -> requires another api than coinlore
//    price, volumes etc
    // convert coinlore call to binance and websockets

    @Autowired
    private DataService dataService;
    @Autowired
    private WebClient coinLoreWebClient;


    @GetMapping(value="/allcoins",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getAllCoins(@RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "10") int limit){
        Flux<String> coins = dataService.getAllCoins(start,limit);
        System.out.println(coins.toString());
        return coins;
    }
    @GetMapping(value = "/market/{coinid}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getMarketsOfCoin(@PathVariable int coinid){
        Flux<String> markets = dataService.getMarketsOfCoin(coinid);
        return markets;
    }
    @PostMapping(value="followedcoins",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFollowedCoind(@RequestBody List<Integer> coinids){
        Flux<String> followedcoins = dataService.getFollowedCoind(coinids);
        return followedcoins;
    }
    @GetMapping(value = "coin",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getCoin(@PathVariable int id){
        Flux<String> coin = dataService.getCoin(id);
        return coin;
    }
    @GetMapping("/test")
    public Mono<String> testApi() {

        return coinLoreWebClient.get()
                .uri("/api/tickers/?start=0&limit=5")
                .retrieve()
                .bodyToMono(String.class);
    }



}
