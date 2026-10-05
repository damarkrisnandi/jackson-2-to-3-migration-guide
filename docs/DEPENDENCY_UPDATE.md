# Dependency Update (step by step)

## Step 1 – Java 17+
Set `maven.compiler.release` / Gradle toolchain to 17 or higher.

## Step 2 – Maven
Use the Jackson BOM so versions stay aligned:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>tools.jackson</groupId>
      <artifactId>jackson-bom</artifactId>
      <version>3.2.3</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>tools.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
  </dependency>
  <!-- annotations remain on the old coordinates -->
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.22</version>
  </dependency>
</dependencies>
```

## Step 3 – Gradle
```kotlin
implementation(platform("tools.jackson:jackson-bom:3.2.3"))
implementation("tools.jackson.core:jackson-databind")
implementation("com.fasterxml.jackson.core:jackson-annotations:2.22")
```

## Step 4 – Mapping table
| Jackson 2 artifact | Jackson 3 |
|---|---|
| `com.fasterxml.jackson.core:jackson-core` | `tools.jackson.core:jackson-core` |
| `com.fasterxml.jackson.core:jackson-databind` | `tools.jackson.core:jackson-databind` |
| `com.fasterxml.jackson.core:jackson-annotations` | unchanged |
| `jackson-datatype-jsr310`, `-jdk8`, `jackson-module-parameter-names` | removed (built in) |
| `com.fasterxml.jackson.dataformat:jackson-dataformat-xml` / `-yaml` | `tools.jackson.dataformat:...` |
| `com.fasterxml.jackson.module:jackson-module-kotlin` | `tools.jackson.module:jackson-module-kotlin` |

## Step 5 – Transitive Jackson 2
Run `mvn dependency:tree -Dincludes=com.fasterxml.jackson` (or `gradle dependencies`). Jackson 2 and 3 can coexist on the classpath because groupIds and packages differ; libraries that still need Jackson 2 keep working with their own mapper.

## Step 6 – Automate package renames
OpenRewrite offers a Jackson 2→3 recipe (confirm the current recipe name in the OpenRewrite docs). Review the diff manually: mapper configuration usually needs hand-editing.

## Security note
Use the latest patched 3.x release (examples use `3.2.3`; 3.0.0 has known vulnerabilities). Each Jackson 3.x release is built against a specific `jackson-annotations` 2.x version (3.2.3 → 2.22); use at least that version, or you will get `ClassNotFoundException` for newer annotations at runtime. In Spring Boot, override with the `jackson-bom.version` property (see example 04).
