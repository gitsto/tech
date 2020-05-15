package sandbox.exercices_jva.exercice4;

public class WordUtils {
	public static boolean nLetterWord(int nLetters, String word) {
		return nLetters == word.length();
	}

	public static boolean nLetterWordOrLonger(int nLetters, String word) {
		return nLetters >= word.length();
	}
	
	public static boolean abcWord(String word, boolean ignorCase) {
		
		String lowerCaseWord = word;
		if (ignorCase) {			
			lowerCaseWord = word.toLowerCase();
		}
		return lowerCaseWord.contains("a") && lowerCaseWord.contains("b") && lowerCaseWord.contains("c");
	}
	
	public static boolean isOoWord(String word) {
		return word.toLowerCase().contains("oo");
	}
}
