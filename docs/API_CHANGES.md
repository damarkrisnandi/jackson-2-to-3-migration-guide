# API Changes

Runnable versions of these snippets are in [`examples/`](../examples).

## ObjectMapper creation
```java
// Jackson 2
ObjectMapper m = new ObjectMapper();
m.registerModule(new JavaTimeModule());
m.configure(SerializationFeature.INDENT_OUTPUT, true);

// Jackson 3
ObjectMapper m = JsonMapper.builder()
        .enable(SerializationFeature.INDENT_OUTPUT)
        .build();
```

## Read / write
Same calls (`writeValueAsString`, `readValue`, ...), but no checked exception.

## Exceptions
```java
// Jackson 2
try { ... } catch (JsonProcessingException e) { ... }
// Jackson 3
try { ... } catch (JacksonException e) { ... }
```

## Custom (de)serializers
```java
// Jackson 2
class D extends JsonDeserializer<Foo> {
  public Foo deserialize(JsonParser p, DeserializationContext c) throws IOException {
    JsonNode n = p.getCodec().readTree(p); ... }
}
// Jackson 3
class D extends ValueDeserializer<Foo> {
  public Foo deserialize(JsonParser p, DeserializationContext c) {
    JsonNode n = c.readTree(p); ... }
}
```
Serializers: `JsonSerializer<T>` → `ValueSerializer<T>`, `SerializerProvider` → `SerializationContext`. `StdSerializer`/`StdDeserializer` keep their names.

## Tree model
`asText()`→`asString()`, `isTextual()`→`isString()`, `fields()`→`properties()`.

## Streaming
`JsonToken.FIELD_NAME`→`PROPERTY_NAME`; `JsonParser.Feature`→`StreamReadFeature`; `mapper.createParser(..)` is available directly on the mapper.

## Modules
```java
JsonMapper.builder().addModule(new MyModule()).build();
```

## Generics
`TypeReference` moves to `tools.jackson.core.type`; `JavaType`/`TypeFactory` usage is unchanged (example 05).
