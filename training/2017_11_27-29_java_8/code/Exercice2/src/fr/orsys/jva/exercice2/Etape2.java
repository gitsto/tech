package fr.orsys.jva.exercice2;

public class Etape2 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		};
		String result1	= StringUtils.betterString(firstnames[0], firstnames[1], (x, y) -> x.length() >= y.length());
		System.out.println("result par taille = " + result1);
		String result2	= StringUtils.betterString(firstnames[0], firstnames[1], (x, y) -> (x.compareTo(y) < 0));
		System.out.println("result par ordre alphabétique = "+result2);
		
		String best	= firstnames[0];
		for (String f: firstnames)
			best	= StringUtils.betterString(best, f, (x, y) -> (x.compareTo(y) < 0));
		System.out.println("best = "+ best);

	}
}
