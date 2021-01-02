package com.demo.java.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScheduledExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        try {
            Future<Integer> future = executorService.schedule(new TaskCallable(), 3_000L, TimeUnit.MILLISECONDS);
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}


