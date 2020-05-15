package fr.orsys.jva.exercice2;

import java.util.Arrays;

public class Etape1 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Am�lie", "Marie", "Eric"
		};
		
		System.out.println("Original : "+Arrays.asList(firstnames));
		// 1- Par longueur du plus petit au plus grand : utiliser la m�thode length()
		Arrays.sort(firstnames, (f1, f2) -> f1.length() - f2.length());
		System.out.println("1- ascendant en taille : " + Arrays.asList(firstnames));
		
		// 2- Par la propri�t� inverse (du plus grand au plus petit),
		Arrays.sort(firstnames, (f1, f2) -> f2.length() - f1.length());
		System.out.println("2- descendant en taille : " + Arrays.asList(firstnames));
		
		// 3- Par ordre alphab�tique sur le premier caract�re
		Arrays.sort(firstnames, (f1, f2) -> f1.charAt(0) - f2.charAt(0));
		System.out.println("3- ordre alphab�tique sur 1er car : " + Arrays.asList(firstnames));
		
		// 4- Les cha�nes de caract�res qui contiennent un �e� en premier, les autres en second.
		Arrays.sort(firstnames, (f1, f2) -> {
			if (f1.contains("e") && !f2.contains("e"))
				return -1;
			else if (!f1.contains("e") && f2.contains("e"))
				return 1;
			else
				return 0;
		});
		System.out.println("4- avec des 'e' en premier : " + Arrays.asList(firstnames));
		Arrays.sort(firstnames, StringUtils::echecker);
		System.out.println("5- avec une reference � une m�thode : " + Arrays.asList(firstnames));
	}
}
