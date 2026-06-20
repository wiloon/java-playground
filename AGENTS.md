# AI Agent Guide

This project uses AI assistance for examples, experiments, and documentation.

## Required reading

1. **[.ai/instructions.md](.ai/instructions.md)** — language rules, coding conventions, demo↔test pairing, and project context

## Project overview

- **Stack**: Java (latest LTS / current JDK), Gradle (Kotlin DSL), JUnit 5
- **Purpose**: Runnable code samples for learning Java language features; each demo has matching JUnit tests (see `.ai/instructions.md`)
- **Entry point**: `./gradlew run` (main class configured in `app/build.gradle.kts`)
