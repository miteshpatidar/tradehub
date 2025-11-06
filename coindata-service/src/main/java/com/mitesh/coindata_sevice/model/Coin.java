package com.mitesh.coindata_sevice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {
    private String id;
    private String symbol;
    @JsonProperty("price_usd")
    private double price;
    @JsonProperty("market_cap_usd")
    private String capital;
}
