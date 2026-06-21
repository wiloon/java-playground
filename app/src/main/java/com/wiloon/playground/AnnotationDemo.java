package com.wiloon.playground;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Java annotations demo: declaring metadata with {@code @interface}, meta-annotations
 * ({@link Retention}, {@link Target}, {@link Documented}), and runtime introspection.
 * <p>
 * {@code @Override} and {@code @SuppressWarnings} use {@link RetentionPolicy#SOURCE} and
 * are compile-time only; {@code @Deprecated} and custom RUNTIME annotations are visible
 * via reflection.
 */
public class AnnotationDemo {

    /** Marks a demo type with a short label; kept at runtime for reflection. */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Documented
    @interface Tag {
        String value();
    }

    /** Marks demo steps; {@link #order()} drives ordering when read reflectively. */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Priority {
        int order();
    }

    @Tag("annotation-demo")
    static final class SampleSubject {

        @Deprecated(since = "demo", forRemoval = false)
        String legacyName = "old";

        String currentName = "playground";

        @Priority(order = 2)
        String second() {
            return "second";
        }

        @Priority(order = 1)
        String first() {
            return "first";
        }
    }

    static String tagOf(Class<?> type) {
        Tag tag = type.getAnnotation(Tag.class);
        return tag != null ? tag.value() : "";
    }

    static List<String> methodsByPriority(Class<?> type) {
        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Priority.class))
                .sorted(
                        Comparator.comparingInt(
                                method -> method.getAnnotation(Priority.class).order()))
                .map(Method::getName)
                .toList();
    }

    static boolean fieldIsDeprecated(Class<?> type, String fieldName) {
        try {
            return type.getDeclaredField(fieldName).isAnnotationPresent(Deprecated.class);
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    static class Base {
        String message() {
            return "base";
        }
    }

    static class Extended extends Base {
        @Override
        String message() {
            return "extended";
        }
    }

    static String extendedMessage() {
        return new Extended().message();
    }

    public static void main(String[] args) {
        demoCustomAnnotations();
        demoBuiltInAnnotations();
        demoRetentionPolicies();
    }

    static void demoCustomAnnotations() {
        System.out.println("=== Custom annotations ===");
        System.out.println("@Tag on SampleSubject: " + tagOf(SampleSubject.class));
        System.out.println("@Priority order: " + methodsByPriority(SampleSubject.class));
        System.out.println();
    }

    static void demoBuiltInAnnotations() {
        System.out.println("=== Built-in annotations ===");
        System.out.println(
                "@Deprecated on legacyName: "
                        + fieldIsDeprecated(SampleSubject.class, "legacyName"));
        System.out.println("@Override behavior: Extended.message() = " + extendedMessage());
        System.out.println();
    }

    static void demoRetentionPolicies() {
        System.out.println("=== Retention policies ===");
        System.out.println("RUNTIME (@Tag, @Deprecated): visible to reflection");
        System.out.println("SOURCE (@Override, @SuppressWarnings): compile-time only");
        System.out.println("CLASS: stored in bytecode, not loaded into the JVM by default");
    }
}
