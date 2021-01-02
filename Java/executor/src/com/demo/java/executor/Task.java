package com.demo.java.executor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("thread id:" + Thread.currentThread().getId());
    }
}

class SlowTask implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread id:" + Thread.currentThread().getId());
    }
}

class TaskCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("thread id:" + Thread.currentThread().getId());
        return new Random().nextInt(100);
    }
}

class SlowTaskCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread id:" + Thread.currentThread().getId());
        return new Random().nextInt(100);
    }
}