package com.cream.helper.core;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExecutorManager {

    private final AtomicInteger scheduledThreadNum = new AtomicInteger(0);
    private final AtomicInteger commonThreadNum = new AtomicInteger(0);


    /**
     * 通用线程池
     */
    private final ExecutorService COMMON_THREAD_POLL = Executors.newCachedThreadPool(
            r -> new Thread(r, "TestHelper Common thread - " + commonThreadNum.getAndIncrement()));

    /**
     * 定时任务线程池
     */
    private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2,
            r -> new Thread(r, "TestHelper定时器线程: " + scheduledThreadNum.getAndIncrement()));


    public ScheduledFuture<?> schedule(Runnable task, long delay, TimeUnit unit) {
        return scheduledExecutor.schedule(task, delay, unit);
    }

    public ScheduledFuture<?> runFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        return scheduledExecutor.scheduleAtFixedRate(task, initialDelay, period, unit);
    }

    public ScheduledFuture<?> runFixedDelay(Runnable task, long initialDelay, long period, TimeUnit unit) {
        return scheduledExecutor.scheduleWithFixedDelay(task, initialDelay, period, unit);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return COMMON_THREAD_POLL.submit(task);
    }

    public void execute(Runnable runnable) {
        COMMON_THREAD_POLL.execute(runnable);
    }
}
