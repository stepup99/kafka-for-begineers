package com.example.demo_kafka_streams.Order.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem {
    @JsonProperty("item")
    String item;

    @JsonProperty("count")
    Integer count;

    @JsonProperty("amount")
    BigDecimal amount;
}
