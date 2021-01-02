package com.demo.java.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTaskInvokeAll {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            List<SlowTaskCallable> tasks = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                tasks.add(new SlowTaskCallable());
            }
            List<Future<Integer>> list = executorService.invokeAll(tasks);
            for (Future<Integer> future : list) {
                Integer result = future.get();
                System.out.println(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}


