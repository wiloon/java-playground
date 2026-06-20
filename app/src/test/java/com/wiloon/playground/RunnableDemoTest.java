package com.wiloon.playground;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class RunnableDemoTest {

    @Test
    void anonymousAndLambdaRunnableRunWithoutError() {
        assertDoesNotThrow(() -> RunnableDemo.runTask(RunnableDemo.anonymousRunnable()));
        assertDoesNotThrow(() -> RunnableDemo.runTask(RunnableDemo.lambdaRunnable()));
    }

    @Test
    void directRunUsesCurrentThread() throws InterruptedException {
        long caller = Thread.currentThread().threadId();
        long worker = RunnableDemo.threadIdAfterDirectRun(() -> {});
        assertEquals(caller, worker);
    }

    @Test
    void newThreadRunsOnDifferentThread() throws InterruptedException {
        long caller = Thread.currentThread().threadId();
        long worker = RunnableDemo.threadIdAfterNewThread(() -> {}, "runnable-demo-test");
        assertNotEquals(caller, worker);
    }

    @Test
    void virtualExecutorRunsOnDifferentThread() throws InterruptedException {
        long caller = Thread.currentThread().threadId();
        long worker = RunnableDemo.threadIdAfterVirtualExecutor(() -> {});
        assertNotEquals(caller, worker);
    }
}
