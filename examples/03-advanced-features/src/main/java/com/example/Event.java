package com.example;

import java.time.Duration;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    public interface Public { }
    public interface Internal extends Public { }

    @JsonView(Public.class)
    public String eventName;

    @JsonView(Public.class)
    public Instant occurredAt;

    @JsonView(Public.class)
    public Duration took;

    @JsonView(Internal.class)
    @JsonProperty("internal_note")
    public String internalNote;

    public Event() { }

    public Event(String eventName, Instant occurredAt, Duration took, String internalNote) {
        this.eventName = eventName;
        this.occurredAt = occurredAt;
        this.took = took;
        this.internalNote = internalNote;
    }
}
