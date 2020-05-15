package fr.orsys.jva.exercice2;

@FunctionalInterface
public interface TwoStringPredicate {
	public boolean isBetter(String s1, String s2);
}
