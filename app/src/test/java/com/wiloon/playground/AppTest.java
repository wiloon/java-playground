package com.wiloon.playground;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test
    void calcPiEstimateIsNearPiWithFixedSeed() {
        Random random = new Random(42);
        double pi = App.calcPi(random, 50_000);
        assertTrue(pi > 3.0 && pi < 3.3, "monte carlo pi estimate should be near 3.14, was " + pi);
    }
}
