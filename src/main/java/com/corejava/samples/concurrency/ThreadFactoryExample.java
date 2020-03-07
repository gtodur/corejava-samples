package com.corejava.samples.concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryExample {

	public static void main(String[] args) {
		ThreadFactoryImpl tfi = new ThreadFactoryImpl();
		for(int i=0; i<10; i++) {
			Thread t = tfi.newThread(new Task());
			try {
				t.start();
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Task implements Runnable {
	AtomicInteger ai = new AtomicInteger(0);
	@Override
	public void run() {
		for (int i = 1; i < 5; i++) {
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            System.out.println("Thread : " + Thread.currentThread().getName() + 
            		" " + " Run method executed " + ai.addAndGet(1) + " times");
        }
		
	}
	
}

class ThreadFactoryImpl implements ThreadFactory {
	private int counter = 1;
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "Thread : "+ counter++);
		return t;
	}
	
}
