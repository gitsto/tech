package fr.orsys.jva.exercice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Etape4 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		};
		List<String> words		= Arrays.asList(firstnames);
		List<String> shortWords = StringUtils.allMatches(words, s -> s.length() <= 4);
		System.out.println(shortWords);
		List<String> wordsWithB = StringUtils.allMatches(words, s -> s.contains("B"));
		System.out.println(wordsWithB);
		List<String> evenLengthWords = StringUtils.allMatches(words, s -> (s.length() % 2) == 0);
		System.out.println(evenLengthWords);
		
		List<Integer> nums = Arrays.asList(1, 10, 100, 1000, 10000);
		List<Integer> bigNums = ElementUtils.allMatches(nums, n -> n>500);
		System.out.println(bigNums);
		
		List<Car> flotte	= new ArrayList<>();
		flotte.add(new Car("C1", 5000.));
		flotte.add(new Car("C2", 10000.));
		flotte.add(new Car("C3", 15000.));
		flotte.add(new Car("C4", 20000.));
		List<Car> lowCost	= ElementUtils.allMatches(flotte, (c) -> c.getPrice() < 8000.);
		System.out.println(lowCost);
	}
}
