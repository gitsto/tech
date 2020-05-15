package fr.orsys.jva.exercice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FolderUtils {
	
	public static void main(String ... args) {
		displayPaths("C:/temp");
	}
	
	public static void displayPaths(String folderName) {
		Path path				= Paths.get(folderName);
		System.out.println(path.toAbsolutePath());
		Stream<Path> streamPath	= null;
		try {
			streamPath	= Files.walk(path);
			streamPath.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			streamPath.close();
		}
	}

	public static long numPathsInTree(String folderName) {
		Path path				= Paths.get(folderName);
		System.out.println(path.toAbsolutePath());
		Stream<Path> streamPath	= null;
		try {
			streamPath	= Files.walk(path);
			return streamPath.count();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			streamPath.close();
		}
		return -1;
	}
}
