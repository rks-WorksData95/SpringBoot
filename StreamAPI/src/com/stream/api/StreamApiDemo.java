package com.stream.api;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamApiDemo {

	public static void main(String[] args) {
		
		//Stream
		
		List<Integer> numbners = Arrays.asList(3, 2, 2, 3, 5, 3, 7);
		System.out.println("Before Filter : "+numbners);
		
		List<Integer> finalNumbners = numbners.stream().filter(i->i==3).collect(Collectors.toList());
		System.out.println("Before Filter : "+finalNumbners);
		
		System.out.println("Random Number");
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
		
		System.out.println("Unique Square Using Map");
		List<Integer> squareLists = numbners.stream().map(i->i*i).distinct().collect(Collectors.toList());
		System.out.println("Square Lists : "+squareLists);
		
		System.out.println("Sorted Random Number");
		Random sortedRandomNumber = new Random();
		sortedRandomNumber.ints().limit(10).sorted().forEach(System.out::println);

		//Parallel Stream
		
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		long count = strings.parallelStream().filter(string->string.isEmpty()).count();
		System.out.println("Empty String Count : "+count);
		
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("Filtered List: " + filtered);
		
		String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("Merged String: " + mergedString);
		
		
		System.out.println("Summery Statistics");
		IntSummaryStatistics stats = numbners.stream().mapToInt((x)->x).summaryStatistics();
		
		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
		
	}

}
