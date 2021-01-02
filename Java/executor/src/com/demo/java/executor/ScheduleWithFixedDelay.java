package com.demo.java.executor;

import java.util.concurrent.*;

public class ScheduleWithFixedDelay {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        try {
            executorService.scheduleWithFixedDelay(new Task(), 3_000L, 3_000L, TimeUnit.MILLISECONDS);
            executorService.scheduleWithFixedDelay(new Task(), 1_000L, 1_000L, TimeUnit.MILLISECONDS);
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}


