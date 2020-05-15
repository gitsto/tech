package sandbox.exercices_jva.exercice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class Etape1 {

	public static void main(String[] args) {

		/*
		 * Ecrire une classe fr.paris.jva.exercice4.Etape1, - Imprimez le premier mot de
		 * 10 lettres trouv�es dans le fichier. Pour ce faire il est souhaitable de
		 * faire une m�thode static nLetterWord dans une classe WordUtils,
		 */
		String fileUri = "C:\\Users\\Administrateur.ADMIN-NVKE0T500\\Desktop\\formationJava8\\eclipse-workspace\\sandbox\\src\\sandbox\\exercices_jva\\exercice4\\enable1-word-list.txt";
		Path p = Paths.get(fileUri);
		try (Stream<String> stream = Files.lines(p)) {
			Optional<String> result = stream.filter(word -> WordUtils.nLetterWord(10, word)).findFirst();
			System.out.println(result.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------");
		/*
		 * - Imprimez le premier mot de 6 lettres qui contient "a", "b" et "c". Pour ce
		 * faire il est souhaitable de faire une m�thode static abcWord dans la classe
		 * WordUtils,
		 */
		p = Paths.get(fileUri);
		try (Stream<String> stream = Files.lines(p)) {
			Optional<String> result = stream.filter(word -> WordUtils.abcWord(word, false))
					.filter(word -> WordUtils.nLetterWord(6, word)).findFirst();
			System.out.println(result.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------");
		/*
		 * - R�p�tez le probl�me pr�c�dent, mais aussi ajouter la possibilit� de casse
		 * mixte des mots dans le fichier. Astuce: faire quelque chose plus simple que
		 * de simplement ajouter trois tests de filtrage suppl�mentaires (pour "A", "B"
		 * et "C").
		 */
		p = Paths.get(fileUri);
		try (Stream<String> stream = Files.lines(p)) {
			Optional<String> result = stream.filter(word -> WordUtils.abcWord(word, true))
					.filter(word -> WordUtils.nLetterWord(6, word)).findFirst();
			System.out.println(result.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------");
		/*
		 * - D�finir une m�thode statique isOoWord dans la classe WordUtils, qui renvoie
		 * vrai seulement pour des mots qui ont au moins deux o cons�cutifs.
		 */
		p = Paths.get(fileUri);
		try (Stream<String> stream = Files.lines(p)) {
			stream.filter(WordUtils::isOoWord)
					.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------");
		/* Compte tenu de cette m�thode, imprimer le premier mot qui a six ou plusieurs lettres,
		 * contient un "b", et est un mot oo. Pour ce faire il est souhaitable de faire
		 * une m�thode static isOoWord dans une classe StringUtils, 
		 */
		p = Paths.get(fileUri);
		try (Stream<String> stream = Files.lines(p)) {
			Optional<String> result = stream
			.filter(word -> StringUtils.isOoWord(6, word))
			.filter(word -> word.contains("b"))
			.findFirst();
			System.out.println(result.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------");		
	
		/*
		 * Exemple de flatmap qui n'a rien � voir avec l'enoncer
		 */
		p = Paths.get(fileUri);
		try (Stream<String> lines = Files.lines(p)) {
			lines.flatMap(s -> Arrays.stream(s.split(" "))).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------");		
		
		 /* 
		 * - Faire un fichier appel� "twitter-words.txt" qui contient tous les mots de la liste de enable1
		 * qui contiennent "wow" ou "cool". Les mots doivent �tre tri�s, en majuscules,
		 * et avoir un point � la fin d'exclamation. (Par exemple, "COOLER!"). Pour ce
		 * faire il est souhaitable de faire une m�thode static storeTwitterList dans
		 * une classe WordUtils, - Imprimez le nombre de fichiers dans votre projet. Les
		 * dossiers comptent comme des fichiers. Pour ce faire il est souhaitable de
		 * faire une m�thode static numPathsInTree dans une classe FolderUtils, - Cr�ez
		 * un fichier contenant 17 doubles al�atoires entre 0 et 100, chacun avec
		 * exactement trois chiffres apr�s la virgule. Pour ce faire il est souhaitable
		 * de faire une m�thode static writeNums dans une classe WritingUtils,
		 */

	}
}
