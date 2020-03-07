package com.corejava.samples.concurrency;

//Class level lock and object level lock puts lock on class and object respectively
//Hence they both can be run concurrently

public class ThreadClassLevelLock {
	
public static void main(String[] args) {
	Runnable r = new Runnable() {
	PrintDigits printDigits = new PrintDigits();	
		@Override
		public void run() {
			try {
				PrintDigits.printDigitsSync(4);
				printDigits.printDigits(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	new Thread(r, "ONE").start();
	new Thread(r, "TWO").start();
}	

}

class PrintDigits {
	public synchronized static void printDigitsSync(int n) throws InterruptedException {
		for(int i=0; i<n; i++) {
			System.out.println("Sync static method " + Thread.currentThread().getName() + " :: " + i);
			Thread.sleep(1000);
		}
		
	}
	
	public void printDigits(int n) throws InterruptedException {
		synchronized(PrintDigits.class) {
			for(int i=0; i<n; i++) {
				System.out.println("Sync static block " + Thread.currentThread().getName() + " :: " + i);
				Thread.sleep(1000);
			}
		}
	}
}
