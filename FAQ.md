# FAQ

**Why did the package change?** So Jackson 2 and 3 can coexist on one classpath.

**Why do annotations still use `com.fasterxml.jackson.annotation`?** So annotated model classes work with both versions.

**Which Java version?** 17+.

**Can I use Jackson 2 and 3 together?** Yes; different groupIds and packages. Keep each mapper separate.

**Do I still need `JavaTimeModule`?** No, java.time is built in.

**Why can't I call `mapper.configure(...)`?** Mappers are immutable. Use `JsonMapper.builder()` or `mapper.rebuild()`.

**Why no more `throws IOException`?** Jackson exceptions are unchecked (`JacksonException`).

**My JSON output changed.** Defaults changed (dates, property ordering). See [docs/CONFIGURATION.md](docs/CONFIGURATION.md).

**Which Spring Boot version?** Spring Boot 4 / Spring Framework 7 use Jackson 3 by default. See [VERSION_MATRIX.md](VERSION_MATRIX.md).

**Is there tooling?** OpenRewrite offers a Jackson 2→3 recipe; review its output manually.
