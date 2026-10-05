# Version Matrix

| Component | Jackson 2 | Jackson 3 |
|---|---|---|
| Java baseline | 8 | 17 |
| Core/databind groupId | `com.fasterxml.jackson.core` | `tools.jackson.core` |
| Annotations groupId / version | `com.fasterxml.jackson.core` / 2.x | same, 2.20 (shared) |
| Packages | `com.fasterxml.jackson.*` | `tools.jackson.*` (annotations unchanged) |
| Spring Boot | 2.x, 3.x | 4.x |
| Spring Framework | 5.x/6.x (Jackson 2 converters) | 7.x (Jackson 3 converters) |
| JSR-310 / JDK8 / param-names | separate modules | built in |

Versions used by the examples: Jackson `3.0.0`, annotations `2.20`, Spring Boot `4.0.0`, Java 17.
Later 3.x releases exist; check Maven Central for the latest.
