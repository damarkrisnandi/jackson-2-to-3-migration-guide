package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

public class AdvancedExample {
    public static void main(String[] args) {
        // 1. Naming strategy, features and stream features: all on the builder.
        ObjectMapper mapper = JsonMapper.builder()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
                .build();

        Event event = new Event("deploy", Instant.parse("2025-01-01T10:00:00Z"),
                Duration.ofSeconds(90), "secret");

        // 2. Views: Jackson 2 mapper.writerWithView(...) -> same API.
        System.out.println(mapper.writerWithView(Event.Public.class).writeValueAsString(event));
        System.out.println(mapper.writerWithView(Event.Internal.class).writeValueAsString(event));

        // 3. Tree model. Jackson 3 JsonNode: asString(), properties(), isString().
        JsonNode tree = mapper.readTree("{\"user\":{\"first_name\":\"Ada\",\"tags\":[\"a\",\"b\"]}}");
        System.out.println(tree.path("user").path("first_name").asString());
        for (Map.Entry<String, JsonNode> e : tree.path("user").properties()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // 4. Tree building: ObjectNode is mutable; use set(...) (returns the node it was called on).
        ObjectNode obj = mapper.createObjectNode();
        obj.put("id", 1);
        obj.putArray("items").add("x").add("y");
        System.out.println(obj);

        // 5. Streaming: JsonParser.nextToken() no longer throws a checked IOException.
        try (JsonParser parser = mapper.createParser("{\"a\":1,\"b\":[true,null]}")) {
            while (parser.nextToken() != null) {
                JsonToken t = parser.currentToken();
                System.out.print(t + " ");
            }
            System.out.println();
        }

        // 6. Derive a differently configured mapper (mappers are immutable).
        ObjectMapper compact = mapper.rebuild().disable(SerializationFeature.INDENT_OUTPUT).build();
        System.out.println(compact.writeValueAsString(event));
    }
}
