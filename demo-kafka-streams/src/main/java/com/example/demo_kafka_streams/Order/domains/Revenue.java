package com.example.demo_kafka_streams.Order.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class Revenue {


    String locationId;
    BigDecimal finalAmount;

    public Revenue(String locationId, BigDecimal finalAmount) {
        this.locationId = locationId;
        this.finalAmount = finalAmount;
    }
}
