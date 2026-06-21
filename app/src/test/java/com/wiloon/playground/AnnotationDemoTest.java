package com.wiloon.playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class AnnotationDemoTest {

    @Test
    void tagAnnotationReadableAtRuntime() {
        assertEquals(
                "annotation-demo",
                AnnotationDemo.tagOf(AnnotationDemo.SampleSubject.class));
    }

    @Test
    void priorityAnnotationOrdersMethods() {
        List<String> ordered =
                AnnotationDemo.methodsByPriority(AnnotationDemo.SampleSubject.class);
        assertEquals(List.of("first", "second"), ordered);
    }

    @Test
    void deprecatedFieldDetectedAtRuntime() {
        assertTrue(
                AnnotationDemo.fieldIsDeprecated(
                        AnnotationDemo.SampleSubject.class, "legacyName"));
        assertFalse(
                AnnotationDemo.fieldIsDeprecated(
                        AnnotationDemo.SampleSubject.class, "currentName"));
    }

    @Test
    void overrideChangesBehavior() {
        assertEquals("extended", AnnotationDemo.extendedMessage());
    }
}
