package com.mitesh.coindata_sevice.service;

import com.mitesh.coindata_sevice.model.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    private WebClient coinLoreWebClient;

    public Flux<String> getAllCoins(int start, int limit){
        try{
            System.out.println("calling coinlore to get coins "+ LocalDateTime.now());
            return Flux.interval(Duration.ofSeconds(2))
                    .flatMap(tick->{
                        String uri = UriComponentsBuilder.fromPath("/api/tickers/")
                                .queryParam("start","{start}")
                                .queryParam("limit","{limit}")
                                .build(start,limit)
                                .toString();
                        System.out.println("https://api.coinlore.net"+uri);
                        return coinLoreWebClient.get()
                                .uri(uri)
                                .retrieve()
                                .bodyToFlux(String.class);
                    });
        }
        catch (Exception e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }

    public Flux<String> getMarketsOfCoin(int coinid) {
        try{
            System.out.println("calling coinlore to get coin market "+ LocalDateTime.now());
            return Flux.interval(Duration.ofSeconds(2))
                    .flatMap(tick->{
                        String uri = UriComponentsBuilder.fromPath("/api/coin/markets/")
                                .queryParam("id","{coinid}")
                                .build(coinid)
                                .toString();
                        System.out.println("https://api.coinlore.net"+uri);
                        return coinLoreWebClient.get()
                                .uri(uri)
                                .retrieve()
                                .bodyToFlux(String.class);
                    });
        }
        catch (Exception e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }

    public Flux<String> getFollowedCoind(List<Integer> coinids) {
        try{
            System.out.println("calling coinlore to get followed coin"+ LocalDateTime.now());
            String ids = coinids.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            System.out.println(ids);
            return Flux.interval(Duration.ofSeconds(2))
                    .flatMap(tick->{
                        String uri = UriComponentsBuilder.fromPath("/api/ticker/")
                                .queryParam("id",ids)
                                .build()
                                .toString();
                        System.out.println("https://api.coinlore.net"+uri);
                        return coinLoreWebClient.get()
                                .uri(uri)
                                .retrieve()
                                .bodyToFlux(String.class);
                    });
        }
        catch (Exception e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }

    public Flux<String> getCoin(int id) {
        try{
            System.out.println("calling coinlore to get followed coin"+ LocalDateTime.now());

            return Flux.interval(Duration.ofSeconds(2))
                    .flatMap(tick->{
                        String uri = UriComponentsBuilder.fromPath("/api/ticker/")
                                .queryParam("id",id)
                                .build()
                                .toString();
                        System.out.println("https://api.coinlore.net"+uri);
                        return coinLoreWebClient.get()
                                .uri(uri)
                                .retrieve()
                                .bodyToFlux(String.class);
                    });
        }
        catch (Exception e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }
}
