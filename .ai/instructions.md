# Project Instructions for AI Agents

## Language requirement (mandatory)

Use **English only** across this repository:

- Source code and identifiers
- Comments and Javadoc
- README and all documentation under `.ai/`
- Commit messages when working in this repo
- Console output strings in demo code

Do **not** add Chinese (or any non-English prose) to files in this project. If the user communicates in another language, respond to the user in their language, but keep project artifacts in English.

## Project layout

```
java-playground/
├── app/src/main/java/com/wiloon/playground/   # demo applications
├── app/src/test/java/com/wiloon/playground/   # JUnit tests
├── app/build.gradle.kts                   # application plugin, Java 21 toolchain
└── settings.gradle.kts
```

## Coding conventions

- Package: `com.wiloon.playground`
- One demo class per topic when possible (e.g. `SamDemo.java`)
- Prefer small, runnable `main` methods over heavy frameworks
- Use `@FunctionalInterface` when demonstrating SAM types
- Match existing Gradle init style; avoid unnecessary dependencies

## Demo classes and unit tests (mandatory)

Every example / demo class under `app/src/main/java/com/wiloon/playground/` **must** have a matching JUnit 5 test class under `app/src/test/java/com/wiloon/playground/`.

| Demo | Test |
| ---- | ---- |
| `FooDemo.java` | `FooDemoTest.java` |
| `App.java` | `AppTest.java` |

Rules:

1. **Pairing** — Adding or renaming a demo requires the corresponding test in the same change (do not merge demo-only PRs).
2. **Purpose** — Tests prove the sample **compiles and its core logic runs**; they are not full integration suites. Prefer extracting testable logic from `main` (package-private/static helpers) over testing `System.out` verbatim.
3. **Coverage** — Each test class should exercise the behaviors the demo illustrates (e.g. SAM implementations produce the same result; `Runnable` runs on a non-main thread when passed to `Thread` / `Executor`).
4. **Verification** — After any demo or test change, run `./gradlew test` (or `./gradlew build`) and ensure it passes before finishing.

`main` methods remain for human exploration via `./gradlew run`; tests are the automated guarantee that examples stay runnable in CI.

## Running and verifying

```bash
./gradlew run          # run configured main class
./gradlew test         # run JUnit tests
./gradlew build        # compile and test
```

When adding a new demo:

1. Add `*DemoTest.java` (or update the existing test for non-`*Demo` classes like `App`).
2. Update `application.mainClass` in `app/build.gradle.kts` or document `-PmainClass=...` in README.
3. Run `./gradlew test` and confirm green.

## Documentation

- Keep README concise: what the project is, how to run, links to demos
- Do not create extra markdown files unless the user asks
