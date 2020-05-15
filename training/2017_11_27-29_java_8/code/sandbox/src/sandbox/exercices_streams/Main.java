package sandbox.exercices_streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		List<String> maListe = Arrays.asList("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf");
		Stream<String> leStream = maListe.stream();
		affiche("c.	Dans la fonction main, filtrer la liste pour ne garder que les chaînes de caractères contenant un « e » et afficher le résultat à l’aide d’une des deux méthodes d’affichage, au choix",
				leStream.filter(s -> s.contains("e")));

		leStream = Stream.of("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf");
		affiche("d.	Dans la fonction main, filtrer la liste pour ne garder que les chaînes de caractères contenant au plus 4 caractères et afficher le résultat à l’aide d’une des...",
				leStream.filter(s -> s.length() <= 4));

		leStream = Stream.of("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf");
		affiche("e.	Dans la fonction main, filtrer la liste pour ne garder que les chaînes de caractères contenant un « e » et ayant au plus 4 caractères ; afficher le résultat à l’aide...",
				leStream.filter(s -> s.contains("e"))
				.peek(s -> System.out.println(" peek 'e' : " + s))
				.filter(s -> s.length() <= 4)
				.peek(s -> System.out.println(" peek '<=4' : " + s))
				);

		Predicate<String> aUnE = s -> s.contains("e");

		leStream = Stream.of("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf");
		affiche("f.	Dans la fonction main, effectuer le même filtre mais afficher les chaînes de caractères en majuscules à l’aide...",
				leStream
				.peek(s -> System.out.println(" original : " + s))
				.filter(aUnE)
				.parallel() // cela ne garanti plus l'ordre
				.peek(s -> System.out.println(" peek 'e' : " + s))
				.filter(aAuPlusNCaracteres(4))
				.peek(s -> System.out.println(" peek '<=4' : " + s))
				.map(s -> s.toUpperCase())
				.peek(s -> System.out.println(" peek 'to UPPERCASE' : " + s))
				);

		// 2- Function, Predicate
		leStream = Stream.of("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf");
		affiche("fbis.	Dans la fonction main, effectuer le même filtre mais afficher les chaînes de caractères en majuscules à l’aide...",
				leStream
				.filter(aUnE.and(aAuPlusNCaracteres(4)))
				.parallel() // cela ne garanti plus l'ordre
				.map(s -> s.toUpperCase())
				.peek(s -> System.out.println(" peek 'to UPPERCASE' : " + s))
				);
		
	}

	public static Predicate<String> aAuPlusNCaracteres(int n) {
		return  s -> s.length() <= n;
	}

	public static <T> void affiche(String prefix, Stream<T> stream) {
		stream.forEach(System.out::println);
	}

}
