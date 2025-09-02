//package com.argusoft.medplat.config;
//
//
//import org.apache.kafka.common.TopicPartition;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.listener.DefaultErrorHandler;
//import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.util.backoff.FixedBackOff;
//
//import java.util.logging.Logger;
////
////@Configuration
////public class KafkaErrorHandlingConfig {
////
////    public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> genericKafkaTemplate) {
////        FixedBackOff backOff = new FixedBackOff(2000L, 3);
////
////        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(genericKafkaTemplate,
////                (record, ex) -> new TopicPartition(record.topic() + "-dlt", record.partition()));
////
////        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);
////
////        // 🔁 Log retry attempts for visibility
////        errorHandler.setRetryListeners((record, ex, deliveryAttempt) -> {
////            System.out.printf("🔁 Retrying (attempt %d) for topic: %s, key: %s%n",
////                    deliveryAttempt, record.topic(), record.key());
////        });
////
////        return errorHandler;
////    }
////}
//
//
//@Configuration
//public class KafkaErrorHandlingConfig {
//
//    @Bean
//    public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> genericKafkaTemplate) {
//        // Retry 3 times with 2 seconds delay
//        FixedBackOff backOff = new FixedBackOff(2000L, 3);
//
//        // Send failed records to <topic>-dlt
//        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
//                genericKafkaTemplate,
//                (record, ex) -> new TopicPartition(record.topic() + "-dlt", record.partition())
//        );
//        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);
//
//
//        return new DefaultErrorHandler(recoverer, backOff);
//    }
//}

package com.argusoft.medplat.config;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorHandlingConfig {

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> genericKafkaTemplate) {
        FixedBackOff backOff = new FixedBackOff(2000L, 3);

        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                genericKafkaTemplate,
                (record, ex) -> new TopicPartition(record.topic() + "-dlt", record.partition())
        );

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);

        errorHandler.setRetryListeners((record, ex, deliveryAttempt) -> {
            System.out.printf("🔁 Retrying (attempt %d) for topic: %s, key: %s%n",
                    deliveryAttempt, record.topic(), record.key());
        });

        return errorHandler;
    }
}