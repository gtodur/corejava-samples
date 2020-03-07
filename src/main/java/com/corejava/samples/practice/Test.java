package com.corejava.samples.practice;

import java.util.Optional;

public class Test {

	public static void main(String[] args) {
		String b = null;
		Optional<String> a = Optional.ofNullable(b);
		System.out.println(a.isPresent()?"Actual value" : "from optional");
		
	}

}
