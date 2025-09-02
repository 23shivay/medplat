//package com.argusoft.medplat.ingestion;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class IngestionModuleRegistry {
//
//    @Autowired
//    private HypertensionIngestionHandler hypertensionIngestionHandler;
//
//
//
//    private final Map<String, ModuleIngestionConfig> topicToConfigMap = new HashMap<>();
//
//    @javax.annotation.PostConstruct
//    public void init() {
//        topicToConfigMap.put("hypertension_topic",
//                new ModuleIngestionConfig("hypertension_topic", "hypertension", hypertensionIngestionHandler));
//
//
//    }
//
//    public IngestionHandler<?> getHandlerForTopic(String topic) {
//        ModuleIngestionConfig config = topicToConfigMap.get(topic);
//        return config != null ? config.getHandler() : null;
//    }
//}


package com.argusoft.medplat.ingestion;

import com.argusoft.medplat.ncddnhdd.dto.MemberHyperTensionDto;
import com.argusoft.medplat.ncddnhdd.model.MemberHypertensionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class IngestionModuleRegistry {

    @Autowired
    private HypertensionIngestionHandler hypertensionIngestionHandler;

    private final Map<String, ModuleIngestionConfig> topicToConfigMap = new HashMap<>();

    @PostConstruct
    public void init() {
        topicToConfigMap.put("hypertension_topic",
                new ModuleIngestionConfig("hypertension_topic", "hypertension", hypertensionIngestionHandler, MemberHyperTensionDto.class));
    }

    public IngestionHandler<?> getHandlerForTopic(String topic) {
        return topicToConfigMap.get(topic).getHandler();
    }

    public Class<?> getTargetClassForTopic(String topic) {
        return topicToConfigMap.get(topic).getPayloadClass();
    }

    public Set<String> topicNames() {
        return topicToConfigMap.keySet();
    }
}
