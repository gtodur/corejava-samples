package com.corejava.samples.concurrency;

//Locks vs Monitors - locks provide necessary support for implementing monitors
//A lock is kind of data which is logically part of an object’s header on the heap memory.
//Monitor is a synchronization construct that allows threads to have both mutual exclusion (using locks) and cooperation
//https://howtodoinjava.com/java/multi-threading/multithreading-difference-between-lock-and-monitor/

public class ThreadSamples {
	
	public static void main(String[] args) throws InterruptedException {
		PrintNumber printNumber = new PrintNumber();
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					//printNumber.printNumbers(4);
					printNumber.printNumbersSynchronized(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		Runnable r1 = () -> System.out.println(Thread.currentThread().getName());
		
		Thread t1 = new Thread(r1, "ONE");
		Thread t2 = new Thread(r1, "TWO");
		System.out.println("Before Join");
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		System.out.println("After Join");
	}
}

class PrintNumber {
	public void printNumbers(int n) throws InterruptedException {
		synchronized (this) {
			for(int i=0; i<n; i++) {
				System.out.println("Sync block " + Thread.currentThread().getName() + " :: " + i);
				Thread.sleep(1000);
			}
		}
	}
	
	public synchronized void printNumbersSynchronized(int n) throws InterruptedException {
			for(int i=0; i<n; i++) {
				System.out.println("Sync method " + Thread.currentThread().getName() + " :: " + i);
				Thread.sleep(1000);
			}
	}
}
