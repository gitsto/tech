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

		affiche("a. Remplacer le paramètres de Collections.sort par une lambda expression", laListe);

		laListe.sort((s1, s2) -> s2.compareTo(s1));
		affiche("b. Afficher ensuite le contenu de la liste trié par ordre alphabétique inverse (en utilisant une lambda expression bien sûr)",
				laListe);

		// rappel : le compareTo renvoi negatif si plus petit et positif si supérieur
		// pour les 2 exo suivants, on passe par un raccourci de l'expression de calcul
		laListe.sort((s1, s2) -> s1.length() - s2.length());
		affiche("c. Afficher ensuite le contenu de la liste trié par longueur de chaîne croissante (en utilisant une lambda)",
				laListe);

		laListe.sort((s1, s2) -> s1.contains("e") ? -1 : 1);
		laListe.sort((s1, s2) -> Main.compareE(s1, s2));
		// ou avec une annotation de fonction
		laListe.sort(Main::compareE);

		// autre manière de faire ci-dessous.
		// laListe.sort((s1, s2) -> { if (s1.contains("e") || !s2.contains("e")) return
		// -1; else return 1; });
		affiche("d.	Afficher ensuite le contenu de la liste en mettant d’abord les chaînes possédant un ‘e’, les autres chaînes ensuite (en utilisant une lambda)",
				laListe);

		// 2 Référence à une méthode

		// a. Dans le tri par ordre alphabétique, remplacer la lambda par une référence
		// à une méthode existante de la classe String
		// b. Dans le tri par ordre alphabétique inverse, remplacer la lambda par une
		// référence à une méthode qu’il faudra écrire

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
