package com.demo.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService.submit(new Task());

            for (int i = 0; i < 10; i++) {
                executorService.submit(new Task());
            }
        } finally {
            executorService.shutdown();
        }
    }
}
