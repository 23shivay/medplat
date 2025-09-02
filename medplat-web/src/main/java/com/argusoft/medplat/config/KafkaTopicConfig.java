package com.argusoft.medplat.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration

public class KafkaTopicConfig {
    //here we are tring to creat new kafka topic for consum,er and producer


    @Bean
    public NewTopic JsonTopic() {
        return TopicBuilder.name("hypertension_topic").build();
    }


}
