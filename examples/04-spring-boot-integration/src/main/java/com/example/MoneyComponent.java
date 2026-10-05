package com.example;

import org.springframework.boot.jackson.JacksonComponent;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/** Jackson 2: @JsonComponent (org.springframework.boot.jackson). Jackson 3: @JacksonComponent. */
@JacksonComponent
public class MoneyComponent {

    public static class Serializer extends ValueSerializer<java.math.BigDecimal> {
        @Override
        public void serialize(java.math.BigDecimal value, JsonGenerator gen, SerializationContext ctxt) {
            gen.writeString(value.toPlainString());
        }
    }
}
