package fr.paris.jva.exercice7;

import java.util.List;
import java.util.Random;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

public class Etape2 {
	private static ToLongFunction<Integer> add(Integer entier) {
		ToLongFunction<Integer> resultat	= (x) -> x + entier;
		return resultat;
	}
	
	public static void main(String[] args) {
		ToLongFunction<Integer> add = add(3);	// additionneur de 3
		System.out.println("operation = "+add.applyAsLong(5));
		Random random				= new Random(System.currentTimeMillis());
		// ints() manipule un Stream d'int
		List<Integer> operandes		= random.ints(20, 0, 100).boxed().collect(Collectors.toList());
		operandes.stream().forEach(System.out::println);
		System.out.println("-----------------");
		operandes.stream().map(x -> add.applyAsLong(x))
							.forEach(System.out::println);
	}
}
