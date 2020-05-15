package sandbox.exercices_jva.exercice4;

public class StringUtils {

	public static boolean isOoWord(int nLetters, String word) {
		return WordUtils.isOoWord(word) && WordUtils.nLetterWordOrLonger(nLetters, word);
	}
	
}
