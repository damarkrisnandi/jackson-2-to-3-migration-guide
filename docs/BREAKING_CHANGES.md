# Breaking Changes

## 1. Platform
- Java baseline is **17** (Jackson 2: Java 8 for most modules).

## 2. Coordinates and packages
| | Jackson 2 | Jackson 3 |
|---|---|---|
| core / databind groupId | `com.fasterxml.jackson.core` | `tools.jackson.core` |
| other modules groupId | `com.fasterxml.jackson.dataformat` etc. | `tools.jackson.dataformat` etc. |
| Java packages | `com.fasterxml.jackson.core/databind/...` | `tools.jackson.core/databind/...` |
| annotations artifact | `com.fasterxml.jackson.core:jackson-annotations` | **unchanged** (version stays 2.x, e.g. 2.22) |
| annotation package | `com.fasterxml.jackson.annotation` | **unchanged** |

Databind annotations such as `@JsonSerialize`, `@JsonDeserialize`, `@JsonNaming`, `@JsonPOJOBuilder` live in `tools.jackson.databind.annotation`.

## 3. Immutable mappers
`ObjectMapper`/`JsonMapper` cannot be reconfigured after construction. `configure()`, `enable()`, `disable()`, `registerModule()`, `setXxx()` are gone from the mapper; use `JsonMapper.builder()` or `mapper.rebuild()`.

## 4. Unchecked exceptions
`JsonProcessingException` (checked, extends `IOException`) is replaced by `tools.jackson.core.JacksonException` (extends `RuntimeException`). Subtypes include `StreamReadException`, `StreamWriteException`, `DatabindException` (`InvalidFormatException`, `MismatchedInputException`, ...). Code catching `IOException` around Jackson calls will no longer compile ("exception never thrown"): catch `JacksonException`.

## 5. Renames
| Jackson 2 | Jackson 3 |
|---|---|
| `JsonSerializer` | `ValueSerializer` |
| `JsonDeserializer` | `ValueDeserializer` |
| `SerializerProvider` | `SerializationContext` |
| `JsonToken.FIELD_NAME` | `JsonToken.PROPERTY_NAME` |
| `JsonNode.fields()` | `properties()` |
| `JsonNode.asText()`, `isTextual()` | `asString()`, `isString()` |
| `p.getCodec().readTree(p)` | `ctxt.readTree(p)` |
| date features in `SerializationFeature`/`DeserializationFeature` | `DateTimeFeature` (`tools.jackson.databind.cfg`) |
| `JsonParser.Feature`/`JsonGenerator.Feature` | `StreamReadFeature`/`StreamWriteFeature` |

## 6. Built-in modules
`jackson-datatype-jsr310`, `jackson-datatype-jdk8`, `jackson-module-parameter-names` are merged into `jackson-databind`. Remove the dependencies and `registerModule` calls.

## 7. Changed defaults
See [CONFIGURATION.md](CONFIGURATION.md): dates as ISO-8601 strings, alphabetical property ordering, stricter failure defaults.

## 8. Removed
Methods deprecated in Jackson 2 were removed. Upgrade to the latest 2.x and fix warnings first.

Verify details in the official release notes for your exact version.
