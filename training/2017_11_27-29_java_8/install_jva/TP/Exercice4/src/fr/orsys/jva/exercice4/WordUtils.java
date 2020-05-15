package fr.orsys.jva.exercice4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordUtils {
	
	// retourne un mot de n caractères à partir d'un stream de mots
	public static String nLetterWord(Stream<String> words, int size) {
		return words.filter(w -> w.length() == size).findFirst().orElse(null);
	}
	
	public static String nLetterWord(String filename, int size) {
		Path path					= Paths.get(filename);
		try {
			Stream<String> lines	= Files.lines(path);
			return WordUtils.nLetterWord(lines, size);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String abcWord(Stream<String> words, int size) {
		return words.filter(w -> w.length() == size)
				.filter(w -> w.contains("a"))
				.filter(w -> w.contains("b"))
				.filter(w -> w.contains("c"))
				.findFirst().orElse(null);
	}
	public static String abcWord(String filename, int size) {
		Path path					= Paths.get(filename);
		try {
			Stream<String> lines	= Files.lines(path);
			return WordUtils.abcWord(lines, size);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String abcWordIgnoreCasse(Stream<String> words, int size) {
		return words.filter(w -> w.length() == size)
				.filter(w -> w.toLowerCase().contains("a"))
				.filter(w -> w.toLowerCase().contains("b"))
				.filter(w -> w.toLowerCase().contains("c"))
				.findFirst().orElse(null);
	}
	public static String abcWordIgnoreCasse(String filename, int size) {
		Path path					= Paths.get(filename);
		try {
			Stream<String> lines	= Files.lines(path);
			return WordUtils.abcWordIgnoreCasse(lines, size);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String ooWord(Stream<String> words, int size) {
		return words.filter(w -> w.length() == size)
				.filter(StringUtils::isOoWord)
				.findFirst().orElse(null);
	}
	public static String ooWord(String filename, int size) {
		Path path					= Paths.get(filename);
		try {
			Stream<String> lines	= Files.lines(path);
			return WordUtils.ooWord(lines, size);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** mettre les mots triés qui contiennent "wow" ou "cool" en majuscule et avec '!' à la fin */
	public static List<String> buildTwitterList(Stream<String> words) {
		return words.filter(w -> w.contains("wow") || w.contains("cool"))
				.map(String::toUpperCase)
				.map(w -> w + "!")
				.sorted()
				.collect(Collectors.toList());
	}
	public static void storeTwitterList(String inputName, String outputName) {
		Path inputPath				= Paths.get(inputName);
		Path outputPath				= Paths.get(outputName);
		try {
			Stream<String> lines	= Files.lines(inputPath);
			List<String> twitter	= buildTwitterList(lines);
			Files.write(outputPath, twitter, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
