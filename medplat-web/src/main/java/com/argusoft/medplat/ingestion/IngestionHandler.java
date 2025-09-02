package com.argusoft.medplat.ingestion;


 // @param <T> The type of data this handler will process.

public interface IngestionHandler<T> {
    void processAndPersist(T payload);
}
