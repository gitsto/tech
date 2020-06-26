package monopoly.metier;

import monopoly.exception.JeSuisFaucheException;

public class CaseTaxeDeLuxe extends Case{

	public CaseTaxeDeLuxe(String nom) {
		super(nom);
	}
	public void sArreterSur(Joueur joueur) throws JeSuisFaucheException {
		joueur.debiter(75);
	}
}
