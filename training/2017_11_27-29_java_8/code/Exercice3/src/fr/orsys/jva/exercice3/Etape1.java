package fr.orsys.jva.exercice3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Etape1 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Homer", "Marge", "Bart", "Lisa", "Maggie", "Apu", "Mandjula", "Carl", "Lenny", "Charles-Montgomery", "Jasper", "Moe"
		};

		List<String> words		= Arrays.asList(firstnames);
		System.out.println("------ original ---------");
		words.stream().forEach(System.out::println);
		
		System.out.println("------ avec espace devant ---------");
		words.stream().forEach((fn) -> System.out.println("  "+fn));
		
		System.out.println("------ avec point d'exclamation ---------");
		List<String> withExclamation	= words.stream()
												.map(fn -> fn + "!")
												.collect(Collectors.toList());
		System.out.println(withExclamation);
		
		System.out.println("------ avec eyes au lieu de 'is' ---------");
		List<String> eyesWords			= words.stream()
												.map(fn -> fn.replace("is", "eyes"))
												.collect(Collectors.toList());
		System.out.println(eyesWords);
		
		System.out.println("------ en capitale ---------");
		List<String> uppercaseWords		= words.stream()
												.map(String::toUpperCase)
												.collect(Collectors.toList());
		System.out.println(uppercaseWords);
		
		System.out.println("------ plus petit que 4 lettres ---------");
		List<String> shortWords			= words.stream()
												.filter(fn -> fn.length() <= 4)
												.collect(Collectors.toList());
		System.out.println(shortWords);
		
		System.out.println("------ qui contient un 'B' ---------");
		List<String> bWords				= words.stream()
												.filter(fn -> fn.contains("B"))
												.collect(Collectors.toList());
		System.out.println(bWords);
		
		System.out.println("------ longueur paire ---------");
		List<String> evenWords			= words.stream()
												.filter(fn -> fn.length() % 2 == 0)
												.collect(Collectors.toList());
		System.out.println(evenWords);
		
		System.out.println("------ AND de filter ---------");
		String andWord					= words.stream()
												.map(String::toUpperCase)
												.filter(fn -> fn.length() <= 4)
												.filter(fn -> fn.contains("E"))
												.findFirst()
												.orElse(null);
		System.out.println(andWord);
		
		System.out.println("------ Step by step ---------");
		List<String> result1			= words.stream()
												.map(String::toUpperCase)
												.collect(Collectors.toList());
		
		System.out.println(result1);
		List<String> result2			= result1.stream()
												.filter(fn -> fn.length() <= 4)
												.collect(Collectors.toList());
		
		System.out.println(result2);
		List<String> result3			= result2.stream()
												.filter(fn -> fn.contains("E"))
												.collect(Collectors.toList());
		
		System.out.println(result3);
		String result4					= result3.stream()
												.findFirst()
												.orElse(null);
		System.out.println(result4);
		
		System.out.println("------ longueur paire rn tableau ---------");
		String[] tabEvenWords				= words.stream()
												.filter(fn -> fn.length() % 2 == 0)
												.toArray(String[]::new);
		System.out.println(Arrays.asList(tabEvenWords));

	}
}
