package com.example.demo_kafka_streams.Order.topology;

import com.example.demo_kafka_streams.Order.domains.Order;
import com.example.demo_kafka_streams.Order.domains.OrderType;
import com.example.demo_kafka_streams.Order.domains.Revenue;
import com.example.demo_kafka_streams.Order.serdes.SerdesFactory;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

public class OrdersTopology {
    public static final String ORDERS = "orders";

    public static final String STORES = "stores";

    public static final String GENERAL_ORDERS = "general_orders";

    public static final String RESTAURANT_ORDERS = "restaurant_orders";


    public static Topology buildTopology() {

        Predicate<String, Order> generalPredicate = (key, order) -> order.getOrderType().equals(OrderType.ORDER);
        Predicate<String, Order> resturantPrdeicate = (key, order) -> order.getOrderType().equals(OrderType.RESTURANT);

        ValueMapper<Order, Revenue> revenueValueMapper = order -> new Revenue(order.getLocationid(), order.getFinalAmount());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        var ordersStream = streamsBuilder.stream(ORDERS, Consumed.with(Serdes.String(), SerdesFactory.orderSerdeUsingGenric()));
        ordersStream.print(Printed.<String, Order>toSysOut().withLabel("order"));


        ordersStream.split(Named.as("general-resturant-stream"))
                .branch(generalPredicate,
                        Branched.withConsumer(generalOrderStream -> {
                            generalOrderStream.print(Printed.<String, Order>toSysOut().withLabel("generalStream"));
                            generalOrderStream
                                    .mapValues((k, v) -> revenueValueMapper.apply(v))
                                    .to(GENERAL_ORDERS, Produced.with(Serdes.String(), SerdesFactory.revenueSerde()));
                        }))
                .branch(resturantPrdeicate,
                        Branched.withConsumer(restaurantOrdersStream -> {
                            restaurantOrdersStream.print(Printed.<String, Order>toSysOut().withLabel("restaurantStream"));
                            restaurantOrdersStream
                                    .mapValues((k, v) -> revenueValueMapper.apply(v))
                                    .to(RESTAURANT_ORDERS, Produced.with(Serdes.String(), SerdesFactory.revenueSerde()));
                        })

                );
        return streamsBuilder.build();
    }


}
