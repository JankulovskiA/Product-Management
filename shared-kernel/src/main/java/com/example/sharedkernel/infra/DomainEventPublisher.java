package com.example.sharedkernel.infra;

import com.example.sharedkernel.events.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DomainEventPublisher {
    public void publish(DomainEvent event);
}
