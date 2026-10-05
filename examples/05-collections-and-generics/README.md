# 05 – Collections and generics

| Jackson 2 | Jackson 3 |
|---|---|
| `com.fasterxml.jackson.core.type.TypeReference` | `tools.jackson.core.type.TypeReference` |
| `JavaType` in `com.fasterxml.jackson.databind` | `tools.jackson.databind.JavaType` |
| `readValue(..)` declares `JsonProcessingException`/`IOException` | no checked exceptions |
| `ObjectMapper` mutable, shared `TypeFactory` | `mapper.getTypeFactory()` still available |

Before: [`before/GenericsExample.java`](before/GenericsExample.java). After: [`src/main/java/com/example/GenericsExample.java`](src/main/java/com/example/GenericsExample.java).

Run: `mvn -q compile exec:java`
