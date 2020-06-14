package fr.jurbert.formation.orsys.jap;

public class Utils {

	public static long getRandomDelay(int min, int max) {
		return (long) (min + (max - min) * Math.random());
	}

	public static void sleep(long delai) {
		try {
			Thread.sleep(delai);
		} catch (InterruptedException e) {
		}
	}

}
