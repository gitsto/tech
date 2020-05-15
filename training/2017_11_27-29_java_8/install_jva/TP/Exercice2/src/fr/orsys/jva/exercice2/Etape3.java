package fr.orsys.jva.exercice2;

public class Etape3 {
	public static void main(String[] args) {
		String[] firstnames	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		};
		String result1	= ElementUtils.betterEntry(firstnames[0], firstnames[1], (x, y) -> x.length() >= y.length());
		System.out.println("result par taille = "+result1);
		String result2	= ElementUtils.betterEntry(firstnames[0], firstnames[1], (x, y) -> (x.compareTo(y) < 0));
		System.out.println("result par ordre alphabétique = "+result2);
		
		String best	= firstnames[0];
		for (String f: firstnames)
			best	= ElementUtils.betterEntry(best, f, (x, y) -> (x.compareTo(y) < 0));
		System.out.println("best = "+ best);
		
		Car c4	 	= new Car("C4", 20000.);
		Car _408	= new Car("408", 25000.);
		Car result	= ElementUtils.betterEntry(c4, _408, (c1, c2) -> c1.getPrice() > c2.getPrice());
		System.out.println("la plus chere = " + result);
		
	}
}
