package com.corejava.samples.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSamples {
	
	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
				new Employee("guru", "todur", "TL", 50000f),
				new Employee("sanath", "bt", "TL", 40000f),
				new Employee("harish", "r", "ATA", 70000f),
				new Employee("vardhaman", "m", "APM", 90000f),
				new Employee("guru", "shetti", "TL", 80000f));
		
		//INTERMEDIATE OPERATORS
		//Stream.filter
		System.out.println("Filter() - starts with g");
		employees.stream().filter(e -> e.getFirstName().startsWith("g")).forEach(System.out::println);
		System.out.println("filter() - findFirst() : " + employees.stream().filter(e -> e.getFirstName().startsWith("g")).findFirst());
		
		//Stream.map
		System.out.println("map() - suffix each firstname with IT");
		employees.stream().map(e -> e.getFirstName().concat(" IT")).forEach(System.out::println);
		
		//Stream.sorted
		Comparator<Employee> designationComparator = Comparator.comparing(Employee::getDesignation);
 		System.out.println("sorted(comparable) - sort by salary");
		employees.stream().sorted().forEach(System.out::println);
		System.out.println("sorted(comparator) - sort by designation");
		employees.stream().sorted(designationComparator).forEach(System.out::println);
		
		//TERMINAL OPERATORS
		//Stream.forEach
		System.out.println("forEach()");
		employees.forEach(System.out::println);
		
		//Stream.collect
		System.out.println("collect() - collect as a list after stream operation");
		employees.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
		
		//Stream.match
		System.out.println("match() - anyMatch : " + employees.stream().anyMatch(e -> e.getFirstName().startsWith("g")));
		System.out.println("match() - allMatch : " + employees.stream().allMatch(e -> e.getFirstName().startsWith("g")));
		System.out.println("match() - noneMatch : " + employees.stream().noneMatch(e -> e.getFirstName().startsWith("l")));
		
		//Stream.count
		System.out.println("count() - count of filtered elements : " + employees.stream().filter(e -> (e.getSalary() > 50000L)).count());
		
		//Stream.reduce -> Identity, accumulator, combiner
		System.out.println("reduce() - reduce operation : " + employees.stream()
		.reduce(0F, (totalSalary, e) -> totalSalary + e.getSalary(), Float::sum));
		
		//Boxed Streams
				System.out.println("Parallel Stream - use parallelStream() instead of stream() to enable parallel processing of streams");
				employees.parallelStream().forEach(System.out::println);
		
		//Boxed Streams
		System.out.println("Boxed streams - example");
		IntStream.of(1,2,3,4,5)
                .boxed().forEach(System.out::println);
		
//		Stream Operations - 
//		Intermediate Operations
//		filter()
//		map()
//		flatMap()
//		distinct()
//		sorted()
//		peek()
//		limit()
//		skip()
		
//		Terminal Operations
//		forEach()
//		forEachOrdered()
//		toArray()
//		reduce()
//		collect()
//		min()
//		max()
//		count()
//		anyMatch()
//		allMatch()
//		noneMatch()
//		findFirst()
//		findAny()
		
	}

}
