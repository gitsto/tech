package fr.orsys.jva.exercice2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Etape6 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		};
		List<String> words		= Arrays.asList(firstnames);
		
		Predicate<String> conjonction	= PredicateUtils.allPassPredicate(s -> s.length() <= 4, s -> s.contains("B"), s -> (s.length() % 2) == 0);
		List<String> result1			= StringUtils.allMatches(words, conjonction);
		System.out.println(result1);
		String result2					= PredicateUtils.firstAllMatch(words.stream(), conjonction);
		System.out.println(result2);
		String result3					= PredicateUtils.firstAllMatch(words.stream(), s -> s.contains("a"), s -> s.length() > 5);
		System.out.println(result3);
		
		Predicate<String> disjonction	= PredicateUtils.anyPassPredicate(s -> s.length() <= 4, s -> s.contains("B"), s -> (s.length() % 2) == 0);
		List<String> result4			= StringUtils.allMatches(words, disjonction);
		System.out.println(result4);
		String result5					= PredicateUtils.firstAnyMatch(words.stream(), disjonction);
		System.out.println(result5);
		String result6					= PredicateUtils.firstAnyMatch(words.stream(), s -> s.contains("a"), s -> s.length() > 5);
		System.out.println(result6);
	}
}
