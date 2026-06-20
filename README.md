# java-playground

Runnable Java language-feature demos for learning. Tracks the **latest JDK** (see `app/build.gradle.kts` for the current language level).

## Demos

| Class | Topic | Run |
| ----- | ----- | --- |
| [`SamDemo`](app/src/main/java/com/wiloon/playground/SamDemo.java) | Custom SAM; anonymous class → lambda → method reference; `Predicate`, `Comparator` | `./gradlew run` |
| [`RunnableDemo`](app/src/main/java/com/wiloon/playground/RunnableDemo.java) | JDK `Runnable` SAM; anonymous class & lambda; `Thread` and virtual-thread executor | `./gradlew run -PmainClass=com.wiloon.playground.RunnableDemo` |

Default `./gradlew run` executes `SamDemo`.

Requires **JDK 21+** on `JAVA_HOME` (Gradle uses the running JVM; JDK 8/17 cannot compile this project).

```bash
./gradlew test          # all demo tests (SamDemo, RunnableDemo, App)
./gradlew build         # compile + test
```

## AI agents

See [AGENTS.md](AGENTS.md) and [.ai/instructions.md](.ai/instructions.md).
