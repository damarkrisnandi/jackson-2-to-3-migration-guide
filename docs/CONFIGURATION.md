# Configuration Changes

## Configure only through builders
```java
JsonMapper mapper = JsonMapper.builder()
    .enable(SerializationFeature.INDENT_OUTPUT)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
    .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .addModule(new MyModule())
    .build();

JsonMapper other = mapper.rebuild().disable(SerializationFeature.INDENT_OUTPUT).build();
```
Per-call configuration remains available via `ObjectReader`/`ObjectWriter`.

## Feature enums
Date/time features moved to `tools.jackson.databind.cfg.DateTimeFeature` (e.g. `WRITE_DATES_AS_TIMESTAMPS`). Parser/generator features are `StreamReadFeature`/`StreamWriteFeature`. If an enum constant is not found, search the `tools.jackson.databind.cfg` package.

## Changed defaults
| Setting | Jackson 2 | Jackson 3 |
|---|---|---|
| `WRITE_DATES_AS_TIMESTAMPS` | enabled | **disabled** (ISO-8601) |
| `WRITE_DURATIONS_AS_TIMESTAMPS` | enabled | **disabled** |
| `SORT_PROPERTIES_ALPHABETICALLY` | disabled | **enabled** |
| `FAIL_ON_TRAILING_TOKENS` | disabled | **enabled** |
| `FAIL_ON_NULL_FOR_PRIMITIVES` | disabled | **enabled** |
| `FAIL_ON_UNKNOWN_PROPERTIES` | enabled | enabled |

Check the release notes for the full list. To preserve old behaviour, set the feature explicitly in the builder. Example 03 shows the alphabetical sorting in its output.

## Spring Boot
`spring.jackson.*` keys keep working; see example 04.
