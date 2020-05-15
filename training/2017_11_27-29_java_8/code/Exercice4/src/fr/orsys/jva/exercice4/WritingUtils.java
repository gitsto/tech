package fr.orsys.jva.exercice4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WritingUtils {
	public static void writeNums(String filename, int size) {
		Path outputPath		= Paths.get(filename);
		Charset alphabet	= Charset.defaultCharset();
		try {
			BufferedWriter writer	= Files.newBufferedWriter(outputPath, alphabet);
			PrintWriter printer		= new PrintWriter(writer);
			Random generateur		= new Random(System.currentTimeMillis());
			for (int index = 0; index < size; index ++) {
				printer.printf("%.3f\n", generateur.nextDouble() * 10);
			}
			printer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** méthode pour écrire un stream de Double de taille size dans un fichier */
	public static void writeStreamNums(String filename, int size) {
		Path outputPath		= Paths.get(filename);
		List<String> doubles = Stream.generate(String::new)
				.map(w -> w + String.format("%.3f", 100*Math.random()))
				.limit(size)
				.collect(Collectors.toList());
		try {
			Files.write(outputPath, doubles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
