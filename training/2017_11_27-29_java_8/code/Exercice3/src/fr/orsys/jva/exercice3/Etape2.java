package fr.orsys.jva.exercice3;

import java.util.Arrays;
import java.util.List;

public class Etape2 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Homer", "Marge", "Bart", "Lisa", "Maggie", "Apu", "Mandjula", "Carl", "Lenny", "Charles-Montgomery", "Jasper", "Moe"
		};

		List<String> words		= Arrays.asList(firstnames);
		System.out.println("------ original ---------");
		words.stream().forEach(System.out::println);
		
		System.out.println("------ uppercase en reduce ---------");
		String upperResult1	= words.stream().reduce("", (s1, s2) -> s1 + s2.toUpperCase());
		System.out.println(upperResult1);
		
		System.out.println("------ uppercase en map reduce ---------");
		String upperResult2	= words.stream().map(String::toUpperCase).reduce("", (s1, s2) -> s1 + s2);
		System.out.println(upperResult2);
		String upperResult3	= words.stream().map(String::toUpperCase).reduce("", String::concat);
		System.out.println(upperResult3);
		String upperResult4	= words.parallelStream().map(String::toUpperCase).reduce("", String::concat);
		System.out.println(upperResult4);
		System.out.println(upperResult3.equals(upperResult4));
		
		System.out.println("------ transformer en CSV ---------");
		String commaWords		= words.stream().reduce((s1, s2) -> s1 + "," + s2).orElse("<empty>");
		System.out.println(commaWords);
		
		System.out.println("------ stream de Double générés ---------");
		List<Double> nums = StreamUtils.randomNumberList(20);
		System.out.println(nums);
		
		System.out.println("------ stream de Integer générés avec pas ---------");
		List<Integer> numPas = StreamUtils.orderedNumberList(50, 5, 20);
		System.out.println(numPas);
		
		System.out.println("------ somme d'Integer d'un stream ---------");
		Integer resultSum	= numPas.stream().reduce(0, Integer::sum);
		System.out.println(resultSum);
		
		System.out.println("------ produit de Double d'un stream en sequence ---------");
		Double resultProduct1	= nums.stream().reduce(1., (d1, d2) -> d1 * d2);
		System.out.println(resultProduct1);
		
		System.out.println("------ produit de Double d'un stream en parallele ---------");
		Double resultProduct2	= nums.stream().parallel().reduce(1., (d1, d2) -> d1 * d2);
		System.out.println(resultProduct2);
	}
}
