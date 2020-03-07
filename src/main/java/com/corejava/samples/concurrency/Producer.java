package com.corejava.samples.concurrency;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable{
	private int MAX_CAPACITY = 0;
	private List<Integer> queue = new ArrayList<>();
	
	public Producer(int capacity, List<Integer> sharedQueue) {
		this.MAX_CAPACITY = capacity;
		this.queue = sharedQueue;
	}

	@Override
	public void run() {
		int counter = 0;
		while(true) {
			try {
				produce(counter++);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void produce(int n) throws InterruptedException {
		synchronized (queue) {
			if(queue.size() == MAX_CAPACITY) {
				 System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + queue.size());
				 queue.wait();
			}
			
			Thread.sleep(500);
			queue.add(n);
			System.out.println("Produced : " + n);
			queue.notifyAll();
		}
	}

}
