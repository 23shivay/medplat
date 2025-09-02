//package com.argusoft.medplat.kafka;
//
//
//import com.argusoft.medplat.ingestion.IngestionHandler;
//import com.argusoft.medplat.ingestion.IngestionModuleRegistry;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class GenericKafkaConsumer {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(GenericKafkaConsumer.class);
//
//    @Autowired
//    private IngestionModuleRegistry ingestionModuleRegistry;
//
//    @KafkaListener(topics = {"hypertension_topic"}, groupId = "group_id")
//    public void listen(ConsumerRecord<String, Object> record) {
//        String topic = record.topic();
//        Object payload = record.value();
//
//        LOGGER.info("Received message from topic {}: {}", topic, payload);
//
//        IngestionHandler handler = ingestionModuleRegistry.getHandlerForTopic(topic);
//
//        if (handler == null) {
//            LOGGER.warn("No handler registered for topic: {}", topic);
//            return;
//        }
//
//        try {
//            handler.processAndPersist(payload);
//        } catch (Exception e) {
//            LOGGER.error("Error while processing message from topic: {}", topic, e);
//        }
//    }
//}


package com.argusoft.medplat.kafka;

import com.argusoft.medplat.ingestion.IngestionModuleRegistry;
import com.argusoft.medplat.ingestion.IngestionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class GenericKafkaConsumer {

    @Autowired
    private IngestionModuleRegistry ingestionModuleRegistry;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @KafkaListener(
            topics = "#{@ingestionModuleRegistry.topicNames}",
            groupId = "group_id",
            containerFactory = "genericKafkaListenerContainerFactory"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Payload JsonNode payload) throws JsonProcessingException {

        IngestionHandler<?> handler = ingestionModuleRegistry.getHandlerForTopic(topic);
        Class<?> clazz = ingestionModuleRegistry.getTargetClassForTopic(topic);
        Object typedPayload = objectMapper.treeToValue(payload, clazz);
        @SuppressWarnings("unchecked")
        IngestionHandler<Object> typedHandler = (IngestionHandler<Object>) handler;
        typedHandler.processAndPersist(typedPayload);
    }

}








//    @KafkaListener(
//
//            topics = "#{@ingestionModuleRegistry.topicNames}",
//            groupId = "group_id",
//            containerFactory = "genericKafkaListenerContainerFactory"
//    )
//    public void listen(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                       @Payload JsonNode payload) {
//
//
//        try {
//            // 🔹 Get handler and target class
//            IngestionHandler<?> handler = ingestionModuleRegistry.getHandlerForTopic(topic);
//            Class<?> clazz = ingestionModuleRegistry.getTargetClassForTopic(topic);
//
//            // 🔹 Convert incoming JsonNode into actual POJO
//            Object typedPayload = objectMapper.treeToValue(payload, clazz);
//
//            // ✅ Type-safe call using unchecked cast
//            @SuppressWarnings("unchecked")
//            IngestionHandler<Object> typedHandler = (IngestionHandler<Object>) handler;
//            typedHandler.processAndPersist(typedPayload);
//
//        } catch (Exception e) {
//            System.err.println("❌ Error while processing message for topic: " + topic);
//            e.printStackTrace();
//        }
//    }