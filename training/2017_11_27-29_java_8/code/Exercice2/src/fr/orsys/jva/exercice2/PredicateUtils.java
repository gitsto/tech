package fr.orsys.jva.exercice2;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateUtils {
	/** Calcule le ET de tous les predicats */
	@SafeVarargs
	public static <T> Predicate<T> allPassPredicate(Predicate<T> ... predicates) {
		Predicate<T> resultat	= (t) -> true;
		for (Predicate<T> predicate : predicates) {
			resultat	= resultat.and(predicate);
		}
		return resultat;
	}
	
	/** Calcule le 1er resultat valide tous les predicats */
	@SafeVarargs
	public static <T> T firstAllMatch(Stream<T> stream, Predicate<T> ... predicates) {
		Predicate<T> conjonction	= allPassPredicate(predicates);
		return stream.filter(conjonction).findFirst().orElse(null);
	}
	
	/** Calcule le OU de tous les predicats */
	@SafeVarargs
	public static <T> Predicate<T> anyPassPredicate(Predicate<T> ... predicates) {
		Predicate<T> resultat	= (t) -> false;
		for (Predicate<T> predicate : predicates) {
			resultat	= resultat.or(predicate);
		}
		return resultat;
	}
	
	/** Calcule le 1er resultat valide un des predicats */
	@SafeVarargs
	public static <T> T firstAnyMatch(Stream<T> stream, Predicate<T> ... predicates) {
		Predicate<T> disjonction	= anyPassPredicate(predicates);
		return stream.filter(disjonction).findFirst().orElse(null);
	}
}
