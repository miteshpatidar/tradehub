package com.mitesh.coindata_sevice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient coinLoreWebClient(){
        String coinLoreBaseUrl = "https://api.coinlore.net";
        return WebClient.builder().baseUrl(coinLoreBaseUrl).build();
    }
    @Bean
    public WebClient binanceWebClient(){
        String binanceBaseUrl = "https://data-api.binance.vision";
        return WebClient.builder().baseUrl(binanceBaseUrl).build();
    }
}
