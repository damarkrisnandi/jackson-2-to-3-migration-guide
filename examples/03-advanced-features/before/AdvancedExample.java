// JACKSON 2 (reference only - not compiled)
package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class AdvancedExample {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);

        JsonNode tree = mapper.readTree("{\"user\":{\"first_name\":\"Ada\"}}");
        System.out.println(tree.path("user").path("first_name").asText());
        Iterator<Map.Entry<String, JsonNode>> it = tree.path("user").fields();
        while (it.hasNext()) { System.out.println(it.next()); }

        try (JsonParser parser = mapper.getFactory().createParser("{\"a\":1}")) {
            while (parser.nextToken() != null) { System.out.println(parser.currentToken()); }
        }

        // Jackson 2 allowed mutating the shared mapper at any time:
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
    }
}
