package com.corejava.samples.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;

//UncaughtExceptionHadnler - catch and treat the unchecked exceptions thrown in a Thread object
//UncaughtExceptionHandler does not execute remaining stmts after exception has occured

public class ExceptionInThreadsSample {

	public static void main(String[] args) {
		
		Thread t = new Thread(new IntegerParser(), "Original Thread");
		t.start();
	}
	
}

class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
	      System.out.printf("Thread: %s\n", t.getId());
	      System.out.printf("ThreadName: %s\n", t.getName());
	      System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
	      System.out.printf("Stack Trace: \n");
	      e.printStackTrace(System.out);
	      System.out.printf("Thread status: %s\n", t.getState());
	      new Thread(new IntegerParser(), "NEW Thread").start();
		// above line will start a new thread to retry the run()
	}
	
}

class IntegerParser implements Runnable {

	@Override
	public void run() {
		Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
		System.out.println(Integer.parseInt("123"));
		System.out.println(Integer.parseInt("ABC"));
		System.out.println(Integer.parseInt("456"));
	}
	
}
