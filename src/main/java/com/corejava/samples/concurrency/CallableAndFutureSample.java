package com.corejava.samples.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableAndFutureSample {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		Factorial factorial = new Factorial(5);
		Future<Integer> factorialFuture = executor.submit(factorial);
		System.out.println("Result from future : " + factorialFuture.get());
		System.out.println("Whether task done : " + factorialFuture.isDone());
		executor.shutdown();
	}

}

class Factorial implements Callable<Integer> {
	
	private Integer n;
	
	AtomicInteger ai = new AtomicInteger();
	
	public Factorial(Integer n) {
		this.n = n;
	}

	@Override
	public Integer call() throws Exception {
		int result = 1;
		
		if(n == 0 || n ==1) {
			result = 1;
		} else {
			for(int i = 2; i<= n; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(1000);
			}
			
		}
		System.out.println("Result for number - " + n + " -> " + result);
		return result;
	}
	
}
