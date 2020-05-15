package fr.orsys.jva.exercice3;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {
	// generer un stream de Double sous forme de liste
	public static List<Double> randomNumberList(int size) {
		Random random	= new Random(System.currentTimeMillis());
		return Stream.generate(random::nextDouble).limit(size).collect(Collectors.toList());
	}

	public static List<Integer> orderedNumberList(int start, int step, int size) {
		return Stream.iterate(start, v -> v + step).limit(size).collect(Collectors.toList());
	}

}
