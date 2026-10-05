# 03 – Advanced features

Covers builder-based configuration, naming strategies, JSON views, tree model, streaming and deriving mappers.

| Jackson 2 | Jackson 3 |
|---|---|
| `mapper.setPropertyNamingStrategy(..)` | `builder.propertyNamingStrategy(..)` |
| `mapper.getFactory().enable(JsonParser.Feature.X)` | `builder.enable(StreamReadFeature.X)` |
| `node.fields()` (Iterator) | `node.properties()` (Set of entries) |
| `node.asText()` / `isTextual()` | `node.asString()` / `isString()` |
| `mapper.getFactory().createParser(..)` | `mapper.createParser(..)` |
| mutate the mapper after creation | `mapper.rebuild()` → new immutable mapper |
| `@JsonView`, `@JsonIgnoreProperties`, `@JsonProperty` | unchanged (`com.fasterxml.jackson.annotation`) |

Before: [`before/AdvancedExample.java`](before/AdvancedExample.java). After: [`src/main/java/com/example`](src/main/java/com/example).

Run: `mvn -q compile exec:java`
