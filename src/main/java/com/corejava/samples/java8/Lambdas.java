package com.corejava.samples.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambdas {
	
	public static void main(String[] args) {
		
		//Lambda expressions are implementations of a Functional Interface. 
		//type of lambda expressions -> depends upon the context in which its used
		
		//Java 8 forEach method - its a default void method in Iterable interface class
		Consumer<Map.Entry<Integer, String>> printMapEntry = System.out::println;
		Consumer<Map.Entry<Integer, String>> concatAction = entry -> {
			entry.setValue("DF " + entry.getValue());
		};
		
		
		List<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		intList.stream().forEach(p -> System.out.println(p));
		System.out.println("-----------------------------------------------");
		Map<Integer, String> emp = new HashMap<>();
		emp.put(1, "guru");
		emp.put(2, "todur");
		emp.entrySet().forEach(printMapEntry);
		emp.entrySet().forEach(concatAction);
		emp.entrySet().forEach(printMapEntry);
		
		///////////////////////////////////////////////////////////////
		//Generating streams of integers and String
		Stream<Integer> intStream = Stream.of(1,2,3);
		intStream.forEach(i -> System.out.println(i));
		
		Supplier<Stream<String>> stringStream = () -> Stream.of("guru", "todur");
		//Supplier to be used in cases where same streams will be operated more than once
		stringStream.get().forEach(s -> System.out.println(s));
		
		//converting stream to list
		List<String> stringList = stringStream.get().collect(Collectors.toList());
		System.out.println("Stream(String) converted to list :");
		for(String stringItem : stringList) {
			System.out.println(stringItem);
			
		}
		
		////////////////////////////////////////////////////////////////
		//Comparator sort java 8 examples
		List<Employee> employees = Arrays.asList(
				new Employee("guru", "todur", "TL", 50000f),
				new Employee("sanath", "bt", "TL", 40000f),
				new Employee("harish", "r", "ATA", 70000f),
				new Employee("vardhaman", "m", "APM", 90000f),
				new Employee("guru", "shetti", "TL", 80000f));
		Supplier<Stream<List<Employee>>> empStream = () -> Stream.of(employees);
		System.out.println("Before sorting");
		empStream.get().forEach(System.out::println);
		Comparator<Employee> firstNameComparator = Comparator.comparing(Employee::getFirstName);
		System.out.println("After sorting");
		Collections.sort(employees, firstNameComparator);
		empStream.get().forEach(System.out::println);
		Collections.sort(employees, firstNameComparator.reversed());
		System.out.println("After reverse sorting");
		empStream.get().forEach(System.out::println);
		
	}

}
