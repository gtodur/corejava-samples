package com.corejava.samples.concurrency;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TODO: Lock implementation, Atomic Integer and volatile - not working. fix required

public class ReentrantLockExample {
	
	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(0);
		Thread [] t = new Thread[5];
//		for(int i=0; i<5; i++) {
//			System.out.println("Started thread : " + i);
//			new Thread(new Printer(), "Thread:"+i).start();
//		}
		
		for(int i=1; i<=3; i++) {
			System.out.println("Started thread : $$$" + i);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					ai.incrementAndGet();
				}
			}, "Thread:"+i).start();
		}
		System.out.println("$$$The doc has been printed : " + ai.get() + " times");
	}

}

class Printer implements Runnable {
	Lock lock = new ReentrantLock();
	AtomicInteger ai = new AtomicInteger(0);
	ActualPrinting actualPrinting = new ActualPrinting();
	@Override
	public void run() {
		lock.lock();
		actualPrinting.print();
		lock.unlock();
	}
}

class ActualPrinting  {
	
	AtomicInteger ai = new AtomicInteger(0);
	volatile int i;
	public void print() {
	      try
	      {
	         Long duration = (long) (Math.random() * 10000);
	         System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds :: Time - " + new Date());
	         Thread.sleep(duration);
	      } catch (InterruptedException e)
	      {
	         e.printStackTrace();
	      } finally
	      {
	         System.out.printf("%s: The document has been printed %d %d no of times \n", Thread.currentThread().getName(), ai.incrementAndGet(), ++i);
	      }
	}
	
}
