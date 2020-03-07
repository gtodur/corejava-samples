package com.corejava.samples.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExample {

	public static void main(String[] args) {
		List<Integer> sharedQueue = new ArrayList<Integer>();
		new Thread(new Producer(5, sharedQueue), "Producer").start();
		new Thread(new Consumer(sharedQueue), "Consumer").start();
	}

}
