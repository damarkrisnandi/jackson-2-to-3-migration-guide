package com.example;

import java.math.BigDecimal;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

/** Accepts {"amount": 12.5, "currency": "EUR"} or the short form "12.50 EUR". */
public class MoneyDeserializer extends StdDeserializer<Money> {

    public MoneyDeserializer() {
        super(Money.class);
    }

    // No "throws IOException": Jackson 3 deserializers throw unchecked JacksonException.
    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) {
        // Jackson 2: p.getCodec().readTree(p) -> Jackson 3: ctxt.readTree(p)
        JsonNode node = ctxt.readTree(p);
        if (node.isString()) {
            String[] parts = node.asString().trim().split("\\s+");
            return new Money(new BigDecimal(parts[0]), parts[1]);
        }
        return new Money(node.get("amount").decimalValue(), node.get("currency").asString());
    }
}
