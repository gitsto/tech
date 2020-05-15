package sandbox.exercices_lambdas;

import java.util.Arrays;
import java.util.List;

public class Main {

	/**
	 * Dans le main, il y a eu plusieurs modif
	 */
	public static void main(String[] args) {
		List<String> laListe = Arrays.asList("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf");
		affiche("0. liste de base", laListe);

		// code d'origine
		// Collections.sort(laListe, (s1, s2) -> ((Comparable) s1).compareTo(s2));

		// 1 Lambda

		laListe.sort((s1, s2) -> s1.compareTo(s2));
		// ou avec une annotation de fonction
		laListe.sort(String::compareTo);

		affiche("a. Remplacer le param�tres de Collections.sort par une lambda expression", laListe);

		laListe.sort((s1, s2) -> s2.compareTo(s1));
		affiche("b. Afficher ensuite le contenu de la liste tri� par ordre alphab�tique inverse (en utilisant une lambda expression bien s�r)",
				laListe);

		// rappel : le compareTo renvoi negatif si plus petit et positif si sup�rieur
		// pour les 2 exo suivants, on passe par un raccourci de l'expression de calcul
		laListe.sort((s1, s2) -> s1.length() - s2.length());
		affiche("c. Afficher ensuite le contenu de la liste tri� par longueur de cha�ne croissante (en utilisant une lambda)",
				laListe);

		laListe.sort((s1, s2) -> s1.contains("e") ? -1 : 1);
		laListe.sort((s1, s2) -> Main.compareE(s1, s2));
		// ou avec une annotation de fonction
		laListe.sort(Main::compareE);

		// autre mani�re de faire ci-dessous.
		// laListe.sort((s1, s2) -> { if (s1.contains("e") || !s2.contains("e")) return
		// -1; else return 1; });
		affiche("d.	Afficher ensuite le contenu de la liste en mettant d�abord les cha�nes poss�dant un �e�, les autres cha�nes ensuite (en utilisant une lambda)",
				laListe);

		// 2 R�f�rence � une m�thode

		// a. Dans le tri par ordre alphab�tique, remplacer la lambda par une r�f�rence
		// � une m�thode existante de la classe String
		// b. Dans le tri par ordre alphab�tique inverse, remplacer la lambda par une
		// r�f�rence � une m�thode qu�il faudra �crire

	}

	public static int compareE(String s1, String s2) {
		if (s1.contains("e"))
			return -1;
		else
			return 1;
	}

	public static void affiche(String prefix, List<String> laListe) {
		System.out.println(prefix + "\n");
		laListe.forEach(suivante -> System.out.println("  " + suivante));
		System.out.println("------------------------------------------------");
	}

}
