# java-playground

Runnable Java language-feature demos for learning. Tracks the **latest JDK** (see `app/build.gradle.kts` for the current language level).

## Demos

| Class | Topic | Run |
| ----- | ----- | --- |
| [`SamDemo`](app/src/main/java/com/wiloon/playground/SamDemo.java) | Custom SAM; anonymous class → lambda → method reference; `Predicate`, `Comparator` | `./gradlew run` |
| [`RunnableDemo`](app/src/main/java/com/wiloon/playground/RunnableDemo.java) | JDK `Runnable` SAM; anonymous class & lambda; `Thread` and virtual-thread executor | `./gradlew run -PmainClass=com.wiloon.playground.RunnableDemo` |

Default `./gradlew run` executes `SamDemo`.

Requires **JDK 26** on `JAVA_HOME` (Gradle uses the running JVM; older JDKs cannot compile this project).

## Run all tests

Use Gradle (via the wrapper):

```bash
./gradlew test
```

This runs every JUnit test under `app/src/test/java/` (currently `SamDemoTest`, `RunnableDemoTest`, and `AppTest`).

To compile and test in one step:

```bash
./gradlew build
```

## AI agents

See [AGENTS.md](AGENTS.md) and [.ai/instructions.md](.ai/instructions.md).
