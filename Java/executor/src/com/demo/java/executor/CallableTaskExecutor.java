package com.demo.java.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTaskExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            List<Future<Integer>> list = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                Future future = executorService.submit(new SlowTaskCallable());
                list.add(future);
            }
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


