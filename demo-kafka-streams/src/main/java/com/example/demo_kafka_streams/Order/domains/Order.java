package com.example.demo_kafka_streams.Order.domains;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("locationid")
    private String locationid;

    @JsonProperty("finalAmount")
    private BigDecimal finalAmount;

    @JsonProperty("orderType")
    private OrderType orderType;

    @JsonProperty("orderLineItems")
    private List<OrderLineItem> orderLineItems;

    @JsonProperty("orderDateTime")
    private LocalDateTime orderDateTime;
}
