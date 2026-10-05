# 01 – Basic serialization / deserialization

Shows the core changes every project hits first.

| Jackson 2 | Jackson 3 |
|---|---|
| `com.fasterxml.jackson.databind.*` | `tools.jackson.databind.*` |
| `new ObjectMapper()` + `configure(...)` | `JsonMapper.builder()...build()` (immutable) |
| `JsonProcessingException` (checked) | `JacksonException` (unchecked) |
| `registerModule(new JavaTimeModule())` | built in, no registration |
| `jackson-datatype-jsr310` dependency | removed (part of `jackson-databind`) |

* Before: [`before/BasicExample.java`](before/BasicExample.java) (not compiled)
* After: [`src/main/java/com/example/BasicExample.java`](src/main/java/com/example/BasicExample.java)

Run: `mvn -q compile exec:java`
