package com.example;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/** Writes Money as the short form "12.50 EUR". */
public class MoneySerializer extends StdSerializer<Money> {

    public MoneySerializer() {
        super(Money.class);
    }

    // Jackson 2: JsonSerializer + SerializerProvider; Jackson 3: ValueSerializer + SerializationContext
    @Override
    public void serialize(Money value, JsonGenerator g, SerializationContext ctxt) {
        g.writeString(value.amount().toPlainString() + " " + value.currency());
    }
}
