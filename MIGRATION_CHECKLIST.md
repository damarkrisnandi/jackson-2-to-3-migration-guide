# Migration Checklist

## Prepare
- [ ] Upgrade to the latest Jackson 2.x and fix deprecation warnings.
- [ ] Upgrade to Java 17+.
- [ ] Add golden-file tests of JSON output to catch default changes.
- [ ] List all Jackson dependencies (`mvn dependency:tree`).

## Dependencies
- [ ] Import `tools.jackson:jackson-bom`.
- [ ] `com.fasterxml.jackson.core:jackson-databind` → `tools.jackson.core:jackson-databind`.
- [ ] Keep `jackson-annotations` on `com.fasterxml.jackson.core`.
- [ ] Remove jsr310 / jdk8 / parameter-names artifacts.
- [ ] Move dataformat/module artifacts to `tools.jackson.*`.

## Code
- [ ] Rename imports (OpenRewrite or search/replace); keep `com.fasterxml.jackson.annotation`.
- [ ] Replace `new ObjectMapper()` + setters with `JsonMapper.builder()`.
- [ ] Remove `registerModule(new JavaTimeModule())` etc.
- [ ] Replace `JsonProcessingException`/`IOException` handling with `JacksonException`.
- [ ] `JsonSerializer`/`JsonDeserializer` → `ValueSerializer`/`ValueDeserializer`; `SerializerProvider` → `SerializationContext`.
- [ ] `getCodec()` → `ctxt`.
- [ ] `asText`/`isTextual`/`fields` → `asString`/`isString`/`properties`.
- [ ] `FIELD_NAME` → `PROPERTY_NAME`.
- [ ] Databind annotation imports → `tools.jackson.databind.annotation`.

## Behaviour
- [ ] Review changed defaults; set old values explicitly if needed.
- [ ] Re-run JSON golden tests.

## Spring Boot
- [ ] Upgrade to Spring Boot 4.
- [ ] `Jackson2ObjectMapperBuilderCustomizer` → `JsonMapperBuilderCustomizer`; `@JsonComponent` → `@JacksonComponent`.
- [ ] Inject the Jackson 3 `JsonMapper`.

## Finish
- [ ] No `com.fasterxml.jackson.databind` imports remain.
- [ ] Full test suite and performance smoke test pass.
