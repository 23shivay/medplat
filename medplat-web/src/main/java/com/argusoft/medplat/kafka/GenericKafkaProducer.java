package com.argusoft.medplat.kafka;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class GenericKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericKafkaProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public GenericKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Object payload) {
        LOGGER.info("Sending payload to Kafka | Topic: {}, Payload: {}", topic, payload);

        Message<Object> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
        LOGGER.info(" ✅ data sent  | Topic: {}, Payload: {}", topic, payload);
    }
}
