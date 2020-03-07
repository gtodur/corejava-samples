package com.corejava.samples.concurrency;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{

	private List<Integer> queue = new ArrayList<>();
	
	public Consumer(List<Integer> sharedQueue) {
		this.queue = sharedQueue;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void consume() throws InterruptedException {
		synchronized (queue) {
			if(queue.isEmpty()) {
				System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + queue.size());
				queue.wait();
			}
			
			Thread.sleep(100);
			Integer i = queue.remove(0);
			System.out.println("Consumed : " + i);
			queue.notifyAll();
		}
	}

}
