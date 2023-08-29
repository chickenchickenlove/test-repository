package com.me.syncronizedtest;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class SyncronziedUtilTest {

    int taskCount = 10;
    int threadSize = 50;


    // @Test
    void test1() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                SyncronziedUtil.simpleFunctionWithoutLock();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "simpleFunctionWithoutLock",
                taskCount,
                skew);
    }

    // @Test
    void test2() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                SyncronziedUtil.simpleFunctionWithLock();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "simpleFunctionWithLock",
                taskCount,
                skew);
    }

    // @Test
    void test3() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                try {
                    SyncronziedUtil.simpleFunctionWithLockSleep();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "simpleFunctionWithLockSleep",
                taskCount,
                skew);
    }


    @Test
    void test4() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                SyncronziedUtil.putWithoutLock(new HashMap<>());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "putWithoutLock",
                taskCount,
                skew);
    }

    @Test
    void test5() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                SyncronziedUtil.putWithLock(new HashMap<>());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "putWithLock",
                taskCount,
                skew);
    }

    @Test
    void test6() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                SyncronziedUtil.getWithoutLock();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "getWithoutLock",
                taskCount,
                skew);
    }

    @Test
    void test7() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadSize);
        executor.setCorePoolSize(threadSize);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            executor.execute(() -> {
                SyncronziedUtil.getWithLock();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long skew = System.currentTimeMillis() - startTime;

        System.out.printf("method : %s, taskCount : %d, duration : %d\n",
                "getWithLock",
                taskCount,
                skew);
    }

}