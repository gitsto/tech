package monopoly.test;

import monopoly.metier.Case;
import monopoly.metier.Plateau;

public class TestPlateau {

	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		Case caseCourante = plateau.getCaseDepart();
		int i=0;
		while(i<50){
			System.out.println(caseCourante.getNom());
			caseCourante = caseCourante.getSuivante();
			i++;
		}
	}
	
}
