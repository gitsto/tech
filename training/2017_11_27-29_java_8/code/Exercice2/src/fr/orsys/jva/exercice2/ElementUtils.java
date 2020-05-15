package fr.orsys.jva.exercice2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ElementUtils {
	public static <T> T betterEntry(T operand1, T operand2, TwoElementPredicate<T> predicate) {
		if (predicate.isBetter(operand1, operand2))
			return operand1;
		return operand2;
	}
	
	public static <T> List<T> allMatches(List<T> list, Predicate<T> predicate) {
		List<T> resultat	= new ArrayList<>(list.size());
		for (T elt : list) {
			if (predicate.test(elt)) 
				resultat.add(elt);
		}
		return resultat;
	}
	
	public static <T, R> List<R> transformedList(List<T> list, Function<T, R> operation) {
		List<R> resultat	= new ArrayList<>(list.size());
		for (T elt : list) {
			resultat.add(operation.apply(elt));
		}
		return resultat;
	}
}
