package sandbox.exercices_jva.exercice2;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> firstnames = Arrays.asList("Lisa", "Bart", "Maggie", "Marge", "Homer", "Apu", "Mandula");

		firstnames.stream()
		.map(s -> s + "!")
		.map(s -> s.replace("is", "EYES"))
		.map(String::toUpperCase)
		.forEach(System.out::println);

	}

}
