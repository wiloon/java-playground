package com.wiloon.playground;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link Runnable} demo: a JDK functional interface (SAM) with one abstract method
 * {@link Runnable#run()}.
 * <p>
 * Runnable is commonly passed to {@link Thread} or an {@link java.util.concurrent.Executor}
 * so work runs on another thread. The same task can be written as an anonymous class,
 * a lambda, or invoked directly via {@link Runnable#run()}.
 */
public class RunnableDemo {

    public static void main(String[] args) throws InterruptedException {
        demoImplementationStyles();
        demoDirectRun();
        demoWithThread();
        demoWithExecutor();
    }

    static Runnable anonymousRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous inner class");
            }
        };
    }

    static Runnable lambdaRunnable() {
        return () -> System.out.println("lambda expression");
    }

    static void runTask(Runnable task) {
        task.run();
    }

    /** Runs {@code task} on the current thread and returns that thread id. */
    static long threadIdAfterDirectRun(Runnable task) {
        AtomicLong threadId = new AtomicLong();
        Runnable wrapped = () -> {
            task.run();
            threadId.set(Thread.currentThread().threadId());
        };
        wrapped.run();
        return threadId.get();
    }

    /** Runs {@code task} on a new {@link Thread} and returns that worker thread id. */
    static long threadIdAfterNewThread(Runnable task, String threadName)
            throws InterruptedException {
        AtomicLong threadId = new AtomicLong();
        CountDownLatch done = new CountDownLatch(1);
        Thread worker = new Thread(
                () -> {
                    task.run();
                    threadId.set(Thread.currentThread().threadId());
                    done.countDown();
                },
                threadName);
        worker.start();
        if (!done.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("timed out waiting for thread: " + threadName);
        }
        return threadId.get();
    }

    /** Runs {@code task} on a virtual-thread executor and returns that worker thread id. */
    static long threadIdAfterVirtualExecutor(Runnable task) throws InterruptedException {
        AtomicLong threadId = new AtomicLong();
        CountDownLatch done = new CountDownLatch(1);
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.execute(
                    () -> {
                        task.run();
                        threadId.set(Thread.currentThread().threadId());
                        done.countDown();
                    });
        }
        if (!done.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("timed out waiting for virtual executor task");
        }
        return threadId.get();
    }

    /** Three equivalent ways to implement Runnable#run() */
    static void demoImplementationStyles() {
        System.out.println("=== Runnable implementation styles ===");
        runTask(anonymousRunnable());
        runTask(lambdaRunnable());
        System.out.println();
    }

    /** Runnable#run() executes on the current thread unless passed to Thread / Executor */
    static void demoDirectRun() {
        System.out.println("=== Direct run() on current thread ===");
        Runnable task =
                () -> System.out.println("thread = " + Thread.currentThread().getName());
        threadIdAfterDirectRun(task);
        System.out.println();
    }

    /** Classic use: Thread constructor accepts a Runnable */
    static void demoWithThread() throws InterruptedException {
        System.out.println("=== Thread(runnable) ===");
        Runnable task =
                () ->
                        System.out.println(
                                "worker thread = " + Thread.currentThread().getName());
        threadIdAfterNewThread(task, "runnable-demo");
        System.out.println();
    }

    /** Executor runs the same Runnable on a managed thread (virtual thread on Java 21) */
    static void demoWithExecutor() throws InterruptedException {
        System.out.println("=== ExecutorService.execute(runnable) ===");
        Runnable task =
                () ->
                        System.out.println(
                                "executor thread = " + Thread.currentThread().getName());
        threadIdAfterVirtualExecutor(task);
        System.out.println();
    }
}
