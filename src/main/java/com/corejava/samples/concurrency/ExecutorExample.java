package com.corejava.samples.concurrency;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//Executes only one thread
		ExecutorService es1 = Executors.newSingleThreadExecutor();
		 
		//Internally manages thread pool of 2 threads
		ExecutorService es2 = Executors.newFixedThreadPool(2);
		 
		//Internally manages thread pool of 10 threads to run scheduled tasks
		ExecutorService es3 = Executors.newScheduledThreadPool(10);
		
		//creates a thread pool executor with minimum thread count 10, maximum threads count 100, 
		//5 milliseconds keep alive time and a blocking queue to watch for tasks in future.
		ExecutorService es4 = new ThreadPoolExecutor(10, 100, 5L, TimeUnit.MILLISECONDS,  
                new LinkedBlockingQueue<Runnable>());
		
		ExecutorService es5 = Executors.newFixedThreadPool(1);
		
		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(1000);
            return "Current time :: " + LocalDateTime.now();
		};
		
		es1.execute(() -> System.out.println("es1 " + Thread.currentThread().getName() + " was here!!!"));
		Future f = es2.submit(() -> System.out.println("runnable task executed"));
		System.out.println(null == f.get() ? "Expected" : "Error"); // submit without future - null returned means success
		Future f1 = es3.submit(() -> System.out.println("runnable non null future task executed"), "SUCCESS");
		System.out.println(" es3 result is : " + f1.get() + "es3 execution was  : " + f1.isDone()); // submit without future - null returned means success
		List<Future<String>> results = es5.invokeAll(Arrays.asList(callableTask, callableTask, callableTask));
		for(Future<String> ff : results) {
			System.out.println("InvokeAll() result : " + ff.get());
		}
		
		es1.shutdown(); //orderly shutdown, previous tasks are executed, no new tasks executed
		es2.shutdownNow(); //shuts all active tasks, returns list of yet to be executed runnable tasks
		es3.awaitTermination(1000, TimeUnit.MILLISECONDS); // wait until specified time after shutdown request
		es4.shutdown();
		es5.shutdown();
	}

}
