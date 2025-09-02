//package com.argusoft.medplat.ingestion;
//
//
//public class ModuleIngestionConfig {
//    private final String topicName;
//    private final String moduleName;
//    private final IngestionHandler<?> handler;
//
//    public ModuleIngestionConfig(String topicName, String moduleName, IngestionHandler<?> handler) {
//        this.topicName = topicName;
//        this.moduleName = moduleName;
//        this.handler = handler;
//    }
//
//    public String getTopicName() {
//        return topicName;
//    }
//
//    public String getModuleName() {
//        return moduleName;
//    }
//
//    public IngestionHandler<?> getHandler() {
//        return handler;
//    }
//}


package com.argusoft.medplat.ingestion;

public class ModuleIngestionConfig {
    private final String topic;
    private final String moduleName;
    private final IngestionHandler<?> handler;
    private final Class<?> payloadClass;

    public ModuleIngestionConfig(String topic, String moduleName, IngestionHandler<?> handler, Class<?> payloadClass) {
        this.topic = topic;
        this.moduleName = moduleName;
        this.handler = handler;
        this.payloadClass = payloadClass;
    }

    public String getTopic() {
        return topic;
    }

    public String getModuleName() {
        return moduleName;
    }

    public IngestionHandler<?> getHandler() {
        return handler;
    }

    public Class<?> getPayloadClass() {
        return payloadClass;
    }
}
