package fr.orsys.jva.exercice4;


public class Etape1 {
	public static void main(String[] args) {
		String filename = "enable1-word-list.txt";
		System.out.println("------------ Imprimez le premier mot de 10 lettres ---------");
		String mot10Lettres = WordUtils.nLetterWord(filename, 10);
		System.out.println(mot10Lettres);
		System.out.println("------------ Imprimez le premier mot de 6 lettres qui contient 'a', 'b' et 'c' ---------");
		String mot6LettresABC = WordUtils.abcWord(filename, 6);
		System.out.println(mot6LettresABC);
		System.out.println("------------ Imprimez le premier mot de 6 lettres sans casse qui contient 'a', 'b' et 'c' ---------");
		String mot6Lettresabc = WordUtils.abcWordIgnoreCasse(filename, 6);
		System.out.println(mot6Lettresabc);
		System.out.println("------------ Imprimez le premier mot de 6 lettres qui contient 'oo' ---------");
		String mot6LettresoO = WordUtils.ooWord(filename, 6);
		System.out.println(mot6LettresoO);
		System.out.println("------------ sauvegardez les mots qui contiennent \"wow\" ou \"cool\". Les mots doivent être triés, en majuscules, et avoir un point à la fin d'exclamation. ---------");
		String outputName	= "fm-word-list.txt";
		WordUtils.storeTwitterList(filename, outputName);
		System.out.println("------------ le nombre de fichiers dans votre projet. ---------");
		String folderName	= "../.";
		long numFiles		= FolderUtils.numPathsInTree(folderName);
		System.out.println(numFiles);
		System.out.println("------------ ecrire un flux de Double dans un fichier. ---------");
		outputName	= "fm-random-nums.txt";
		WritingUtils.writeNums(outputName, 17);
		outputName	= "lde-random-nums.txt";
		WritingUtils.writeNums(outputName, 17);
	}
}
