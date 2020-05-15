package fr.orsys.jva.exercice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Etape5 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		};
		List<String> words		= Arrays.asList(firstnames);
		List<String> excitingWords = StringUtils.transformedList(words, s -> s + "!");
		System.out.println(excitingWords);
		List<String> eyeWords = StringUtils.transformedList(words, s -> s.replace("is", "eyes"));
		System.out.println(eyeWords);
		List<String> upperCaseWords = StringUtils.transformedList(words, String::toUpperCase);
		System.out.println(upperCaseWords);
		
		List<Integer> wordLengths = ElementUtils.transformedList(words, String::length);
		System.out.println(wordLengths);
		
		List<Car> flotte	= new ArrayList<>();
		flotte.add(new Car("C1", 5000.));
		flotte.add(new Car("C2", 10000.));
		flotte.add(new Car("C3", 15000.));
		flotte.add(new Car("C4", 20000.));
		List<Double> priceList	= ElementUtils.transformedList(flotte, Car::getPrice);
		System.out.println(priceList);
	}
}
