package com.demo.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 30; i++) {
                executorService.submit(new SlowTask());
            }
        } finally {
            executorService.shutdown();
        }
    }
}


