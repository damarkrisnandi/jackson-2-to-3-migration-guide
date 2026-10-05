// JACKSON 2 (reference only - not compiled)
package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigDecimal;

public class MoneyDeserializer extends StdDeserializer<Money> {
    public MoneyDeserializer() { super(Money.class); }

    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        if (node.isTextual()) {
            String[] parts = node.asText().trim().split("\\s+");
            return new Money(new BigDecimal(parts[0]), parts[1]);
        }
        return new Money(node.get("amount").decimalValue(), node.get("currency").asText());
    }
}
