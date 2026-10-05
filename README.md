# Jackson 2 → Jackson 3 Migration Guide

A practical guide, with compiled and runnable examples, for moving from Jackson 2.x (`com.fasterxml.jackson`) to Jackson 3.x (`tools.jackson`).

> Verified with Jackson `3.0.0`, Java 17 and Spring Boot `4.0.0`. Always cross-check against the [official Jackson 3 release notes](https://github.com/FasterXML/jackson/wiki/Jackson-Release-3.0).

## TL;DR

1. Java **17+** required.
2. Maven groupId `com.fasterxml.jackson.core` → **`tools.jackson.core`** (core, databind); other modules (`dataformat`, `datatype`, `module`) move to `tools.jackson.*` too.
3. Java packages `com.fasterxml.jackson.{core,databind,...}` → **`tools.jackson.{core,databind,...}`**.
4. **Exception**: `jackson-annotations` keeps `com.fasterxml.jackson.core` and package `com.fasterxml.jackson.annotation`.
5. `ObjectMapper` is **immutable** – build via `JsonMapper.builder()`.
6. Exceptions are **unchecked** (`JacksonException extends RuntimeException`).
7. JSR-310, Jdk8 and parameter-names modules are **built in**.
8. Several **default settings changed**.

## Table of contents

| Document | Purpose |
|---|---|
| [MIGRATION_CHECKLIST.md](MIGRATION_CHECKLIST.md) | Step-by-step checklist |
| [docs/BREAKING_CHANGES.md](docs/BREAKING_CHANGES.md) | All breaking changes |
| [docs/DEPENDENCY_UPDATE.md](docs/DEPENDENCY_UPDATE.md) | Maven/Gradle dependency updates |
| [docs/API_CHANGES.md](docs/API_CHANGES.md) | Renamed/changed APIs |
| [docs/CONFIGURATION.md](docs/CONFIGURATION.md) | Configuration and defaults |
| [docs/PERFORMANCE_TIPS.md](docs/PERFORMANCE_TIPS.md) | Performance advice |
| [docs/TROUBLESHOOTING.md](docs/TROUBLESHOOTING.md) | Common errors and fixes |
| [FAQ.md](FAQ.md) | Frequently asked questions |
| [VERSION_MATRIX.md](VERSION_MATRIX.md) | Compatibility matrix |

## Examples

Each example has a `pom.xml` (Jackson 3), a `before/` folder (Jackson 2 code, reference only, not compiled) and the migrated code in `src/main/java`.

| Example | Topic |
|---|---|
| [01-basic-serialization](examples/01-basic-serialization) | Mapper builder, unchecked exceptions, java.time |
| [02-custom-deserializer](examples/02-custom-deserializer) | `ValueSerializer`/`ValueDeserializer`, modules |
| [03-advanced-features](examples/03-advanced-features) | Naming, views, tree model, streaming, `rebuild()` |
| [04-spring-boot-integration](examples/04-spring-boot-integration) | Spring Boot 4 |
| [05-collections-and-generics](examples/05-collections-and-generics) | `TypeReference`, `JavaType` |

Run an example: `cd examples/01-basic-serialization && mvn -q compile exec:java`

## License

See [LICENSE](LICENSE).
