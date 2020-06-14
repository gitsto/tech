package monopoly.metier;

import monopoly.exception.JeSuisFaucheException;

public class CaseDepart extends Case{

	public CaseDepart(String nom){
		super(nom);
	}
	
	public void sArreterSur(Joueur joueur)throws JeSuisFaucheException {
		joueur.crediter(400);
	}
	
	
	public void survoler(Joueur joueur) {
		joueur.crediter(200);
	}
}
