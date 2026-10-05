# 04 – Spring Boot integration

Requires **Spring Boot 4.x / Spring Framework 7**, which uses Jackson 3 by default. Spring Boot 3.x stays on Jackson 2.

| Spring Boot 3 / Jackson 2 | Spring Boot 4 / Jackson 3 |
|---|---|
| `ObjectMapper` bean | `JsonMapper` bean (`tools.jackson.databind.json.JsonMapper`); `ObjectMapper` is its supertype |
| `Jackson2ObjectMapperBuilder` | `JsonMapper.Builder` |
| `Jackson2ObjectMapperBuilderCustomizer` | `JsonMapperBuilderCustomizer` |
| `@JsonComponent` | `@JacksonComponent` |
| `jackson-datatype-jsr310`, `jdk8`, `parameter-names` | built in |
| `MappingJackson2HttpMessageConverter` | `JacksonJsonHttpMessageConverter` |
| `spring.jackson.*` | same keys, bound to Jackson 3 features |

Verify package/class names against your exact Spring Boot version; Spring Boot 4 also ships a temporary `spring-boot-jackson2` bridge for projects that cannot migrate yet.

Before: [`before/JacksonConfig.java`](before/JacksonConfig.java). After: [`src/main/java/com/example`](src/main/java/com/example).

Run: `mvn spring-boot:run`, then `curl localhost:8080/orders/1`.
