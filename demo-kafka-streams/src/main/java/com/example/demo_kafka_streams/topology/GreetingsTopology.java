package com.example.demo_kafka_streams.topology;

import com.example.demo_kafka_streams.domains.Greeting;
import com.example.demo_kafka_streams.serdes.SerdesFactory;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;


@Component
public class GreetingsTopology {

    public static String GREETINGS = "greetings";

    public static String GREETINGS_UPPERCASE = "greetings_uppercase";

    public static String GREETINGS_SPANISH = "greetingsspanish";


    public static Topology buildTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        var mergeStream = getCustomGreetingKStream(streamsBuilder);


        mergeStream.print(Printed.<String, Greeting>toSysOut().withLabel("modifiedKafka"));

        var modifiedStream = mergeStream.mapValues((k, v) -> {
            return new Greeting(v.getMessage().toUpperCase(), v.getTimeStamp());
        });

        modifiedStream.to(
                GREETINGS_UPPERCASE,
                Produced.with(Serdes.String(), SerdesFactory.greetingSerdeUsingGenric())
        );

        return streamsBuilder.build();
    }

    private static KStream<String, Greeting> getCustomGreetingKStream(StreamsBuilder streamsBuilder) {
        //        var greetingsStreams = streamsBuilder.stream(GREETINGS
//                ,
//                Consumed.with(Serdes.String(), Serdes.String())
//        );

        var greetingsStreams = streamsBuilder.stream(GREETINGS
                ,
                Consumed.with(Serdes.String(), SerdesFactory.greetingSerdeUsingGenric())
        );


        var greetinsSpanishStream = streamsBuilder.stream(GREETINGS_SPANISH,
                Consumed.with(Serdes.String(), SerdesFactory.greetingSerdeUsingGenric()));


        var mergeStream = greetingsStreams.merge(greetinsSpanishStream);


//        mergeStream.print(Printed.<String, Greeting>toSysOut().withLabel("mergeStream"));
//
//        var modifiedStreams = mergeStream.mapValues((readOnlyKey, value) -> value.toString());


//        var modifiedStreams = greetingsStreams
//                .filter((key, value) -> value.length() > 5)
//                .peek((key, value) -> {
//                    System.out.println("after filter : key " + key + " --- value " + value);
//                });
//                .flatMap((key, value) -> {
//                    var newValues = Arrays.asList(value.split(""));
//                    return newValues
//                            .stream()
////                            .map(val -> KeyValue.pair(key, val))
////                            .collect(Collectors.toList());
//                            .map(val -> KeyValue.pair(key, val.toUpperCase()))
//                            .collect(Collectors.toList());
//                });
//                .filter((key, value) -> value.length() > 5)
//                .mapValues((readOnly, value) -> value.toUpperCase() + "newData");
//                .map((key, value) -> {
//                    if (key == null || value == null) {
//                        return null;
//                    }
//                    return KeyValue.pair(key.toUpperCase(), value.toLowerCase() + " map");
//                });
        return mergeStream;
    }


}
