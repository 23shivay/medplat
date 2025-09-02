//package com.argusoft.medplat.config;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConsumerConfig {
//
//    private static final String BOOTSTRAP_SERVER = "localhost:9092";
//
//    @Bean
//    public ConsumerFactory<String, JsonNode> genericConsumerFactory() {
//        JsonDeserializer<JsonNode> jsonDeserializer = new JsonDeserializer<>(JsonNode.class);
//        jsonDeserializer.addTrustedPackages("*"); // Trust all packages
//        jsonDeserializer.setRemoveTypeHeaders(false);
//        jsonDeserializer.setUseTypeMapperForKey(false);
//
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
//    }
//
//    @Bean(name = "genericKafkaListenerContainerFactory")
//    public ConcurrentKafkaListenerContainerFactory<String, JsonNode> genericKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(genericConsumerFactory());
//        return factory;
//    }
//}

package com.argusoft.medplat.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
@Configuration


public class KafkaConsumerConfig {

    private static final String BOOTSTRAP_SERVER = "localhost:9092";

    @Bean
    public ConsumerFactory<String, JsonNode> genericConsumerFactory() {
        JsonDeserializer<JsonNode> jsonDeserializer = new JsonDeserializer<>(JsonNode.class);
        jsonDeserializer.addTrustedPackages("*");

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }
//
//    @Bean(name = "genericKafkaListenerContainerFactory")
//    public ConcurrentKafkaListenerContainerFactory<String, JsonNode> genericKafkaListenerContainerFactory(
//            DefaultErrorHandler errorHandler) {
//
//        ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(genericConsumerFactory());
//        factory.setCommonErrorHandler(errorHandler); // ✅ Works now
//        return factory;
//    }
@Bean(name = "genericKafkaListenerContainerFactory")
public ConcurrentKafkaListenerContainerFactory<String, JsonNode> genericKafkaListenerContainerFactory(
        DefaultErrorHandler errorHandler) {

    ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(genericConsumerFactory());
    factory.setCommonErrorHandler(errorHandler);
    return factory;
}
}
