package fr.orsys.jva.exercice2;

@FunctionalInterface
public interface TwoElementPredicate<T> {
	public boolean isBetter(T operand1x, T operand2);
}
