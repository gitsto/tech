package fr.orsys.jva.exercice1;

import java.util.Arrays;
import java.util.List;

public class Etape3 {
	public static void forEach(List<String> list, Formatter fmt) {
		for (String string : list) {
			System.out.print(fmt.format("%s\n", string));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		List<String> firstnames	= Arrays.asList(new String[] {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		});
		// Utilisation de l'interface fonctionnelle pour typer String::format
		// par défaut String::format n'est pas utilisable sous la forme lambda
		forEach(firstnames, String::format);
		System.out.println("-----------");
		forEach(firstnames, (fmt, strings) -> String.format(fmt, strings));
	}
}
