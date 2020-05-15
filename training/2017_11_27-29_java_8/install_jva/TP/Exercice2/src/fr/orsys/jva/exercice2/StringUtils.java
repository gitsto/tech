package fr.orsys.jva.exercice2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class StringUtils {
	public static int echecker(String s1, String s2) {
		if (s1.contains("e") && !s2.contains("e"))
			return -1;
		else if (!s1.contains("e") && s2.contains("e"))
			return 1;
		else
			return 0;
	}
	
	public static String betterString (String s1, String s2, TwoStringPredicate predicate) {
		if (predicate.isBetter(s1,s2))
			return s1;
		else
			return s2;
	}
	
	public static List<String> allMatches (List<String> list, Predicate<String> predicate) {
		List<String> resultat	= new ArrayList<>(list.size());
		for (String elt : list) {
			if (predicate.test(elt)) 
				resultat.add(elt);
		}
		return resultat;
	}
	
	public static List<String> transformedList(List<String> list, Function<String, String> operation) {
		List<String> resultat	= new ArrayList<>(list.size());
		for (String elt : list) {
			resultat.add(operation.apply(elt));
		}
		return resultat;
	}
}
