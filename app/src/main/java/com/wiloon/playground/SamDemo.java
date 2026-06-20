package com.wiloon.playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * SAM (Single Abstract Method) demo: an interface with exactly one abstract method.
 * <p>
 * Since Java 8, such interfaces can be implemented with lambdas or method references;
 * the compiler generates bytecode equivalent to an anonymous inner class.
 */
public class SamDemo {

    /**
     * Custom SAM with one abstract method {@link #apply(String)}.
     * {@code @FunctionalInterface} tells the compiler to reject more than one abstract method.
     */
    @FunctionalInterface
    interface StringLength {
        int apply(String text);
    }

    public static void main(String[] args) {
        demoCustomSam();
        demoJdkSam();
    }

    static StringLength anonymousStringLength() {
        return new StringLength() {
            @Override
            public int apply(String text) {
                return text.length();
            }
        };
    }

    static StringLength lambdaStringLength() {
        return text -> text.length();
    }

    static StringLength methodRefStringLength() {
        return String::length;
    }

    static int lengthOf(StringLength fn, String text) {
        return fn.apply(text);
    }

    static boolean isNonBlank(String text) {
        Predicate<String> nonEmpty = s -> !s.isBlank();
        return nonEmpty.test(text);
    }

    static List<String> sortedByLength(List<String> words) {
        List<String> copy = new ArrayList<>(words);
        copy.sort(Comparator.comparing(String::length));
        return copy;
    }

    /** Same logic, three classic implementation styles */
    static void demoCustomSam() {
        String sample = "hello SAM";

        StringLength anonymous = anonymousStringLength();
        StringLength lambda = lambdaStringLength();
        StringLength methodRef = methodRefStringLength();

        System.out.println("=== Custom SAM: StringLength ===");
        System.out.println("anonymous : " + anonymous.apply(sample));
        System.out.println("lambda    : " + lambda.apply(sample));
        System.out.println("methodRef : " + methodRef.apply(sample));
        System.out.println();
    }

    /** Common built-in SAM types from the JDK */
    static void demoJdkSam() {
        Runnable task = () -> System.out.println("Runnable: works the same inside a thread pool");
        task.run();

        System.out.println("Predicate: \"  \" is non-empty? " + isNonBlank("  "));

        List<String> words = Arrays.asList("banana", "fig", "apple");
        System.out.println("Comparator sort by length: " + sortedByLength(words));
    }
}
