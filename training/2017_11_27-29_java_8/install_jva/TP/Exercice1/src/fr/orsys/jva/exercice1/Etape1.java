package fr.orsys.jva.exercice1;

import static java.lang.System.out;

import java.util.Arrays;

public class Etape1 {
	
	protected Runnable runnable	= () -> out.println(this);
	
	public static void main(String[] args) {
		out.println("Exercice1");
		Etape1 etape1 = new Etape1();
		etape1.runnable.run();
		
		String[] strString	= {
				"Pierre", "Jean", "Philippe", "Bob", "Gaston", "Amélie", "Marie"
		};
		// tri par longueur
		Arrays.sort(strString, (s1, s2) -> s1.length() - s2.length());
		for (String string : strString) {
			out.println(string);
		}
	}
	
	@Override
	public String toString() {
		return getClass().getCanonicalName();
	}
}
