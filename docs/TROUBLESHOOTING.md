# Troubleshooting

| Symptom | Cause / Fix |
|---|---|
| `package com.fasterxml.jackson.databind does not exist` | Moved to `tools.jackson.databind`. |
| `package com.fasterxml.jackson.annotation does not exist` | Add `com.fasterxml.jackson.core:jackson-annotations:2.22`; annotations did not move. |
| `exception IOException is never thrown in body of try` | Jackson 3 throws unchecked `JacksonException`; catch that. |
| `cannot find symbol: configure/registerModule` | Mapper is immutable; use `JsonMapper.builder()`. |
| `cannot find symbol: JsonSerializer` | Use `ValueSerializer`; `SerializerProvider` → `SerializationContext`. |
| `cannot find symbol: JavaTimeModule` | Built in; remove module and dependency. |
| `getCodec()` missing in deserializer | Use `ctxt.readTree(p)` / `ctxt.readValue(p, Type.class)`. |
| `FIELD_NAME` missing | Renamed `PROPERTY_NAME`. |
| JSON output has different property order or date format | Changed defaults; set features explicitly (see CONFIGURATION.md). |
| `@JsonSerialize(using=...)` not found | Import from `tools.jackson.databind.annotation`. |
| Annotations ignored at runtime | A Jackson 2 mapper may be handling the type; make sure the Jackson 3 mapper is injected. |
| Spring: no bean of `com.fasterxml...ObjectMapper` | Spring Boot 4 exposes the Jackson 3 `JsonMapper`; inject that. |
| `NoSuchMethodError`/`ClassNotFoundException` for Jackson classes | A library built against Jackson 2; both can coexist, check `mvn dependency:tree`. |
