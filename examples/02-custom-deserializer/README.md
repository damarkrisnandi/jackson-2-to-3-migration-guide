# 02 – Custom serializer / deserializer

| Jackson 2 | Jackson 3 |
|---|---|
| `JsonSerializer<T>` / `JsonDeserializer<T>` | `ValueSerializer<T>` / `ValueDeserializer<T>` (`StdSerializer`/`StdDeserializer` keep their names) |
| `SerializerProvider` | `SerializationContext` |
| `throws IOException` | none – unchecked `JacksonException` |
| `p.getCodec().readTree(p)` | `ctxt.readTree(p)` |
| `node.isTextual()` / `asText()` | `node.isString()` / `asString()` |
| `@JsonSerialize` in `com.fasterxml.jackson.databind.annotation` | `tools.jackson.databind.annotation` |
| `mapper.registerModule(m)` | `JsonMapper.builder().addModule(m).build()` |

Before: [`before/`](before/) (not compiled). After: [`src/main/java/com/example`](src/main/java/com/example).

Run: `mvn -q compile exec:java`
