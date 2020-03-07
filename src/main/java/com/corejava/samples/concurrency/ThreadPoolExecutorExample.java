package com.corejava.samples.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

	public static void main(String[] args) {
		//1. Fixed thread pool
		ThreadPoolExecutor fExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		for (int i = 1; i <= 5; i++)
        {
            ATask task = new ATask("Task " + i);
            System.out.println("Created : " + task.getName());
 
            fExecutor.execute(task);
        }
		fExecutor.shutdown();
		
		//2. Cached thread pool
		ThreadPoolExecutor cExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		//3. Scheduled thread pool executor
		ThreadPoolExecutor sExecutor = (ThreadPoolExecutor) Executors.newScheduledThreadPool(10);
		//ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit)
		//ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit)
		//ScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, long delay, TimeUnit unit) - second delay is interval
		//ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
		
		//4. Single thread pool executor
		ThreadPoolExecutor singleExecutor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
		
		//5. Work stealing thread pool executor
		ThreadPoolExecutor wExecutor = (ThreadPoolExecutor) Executors.newWorkStealingPool(4);
	}

}

class ATask implements Runnable {
    private String name;
 
    public ATask(String name) {
        this.name = name;
    }
 
    public String getName() {
        return name;
    }
 
    public void run() {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.println("Executing : " + name);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
