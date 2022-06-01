package com.example.sharedkernel.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.Instant;

@Data
@NoArgsConstructor
public  class DomainEvent {

    @JsonProperty("topic")
    private String topic;

    public DomainEvent(String topic) {
        this.topic = topic;
    }


    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        //String output = null;
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null ;
        }
    }

    public String topic() {
        return topic;
    }


    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(json);
        return objectMapper.readValue(reader,eventClass);
    }


}
