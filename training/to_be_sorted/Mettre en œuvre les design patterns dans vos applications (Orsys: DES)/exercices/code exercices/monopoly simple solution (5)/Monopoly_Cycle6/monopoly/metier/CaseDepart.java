package monopoly.metier;

import monopoly.exception.JeSuisFaucheException;

public class CaseDepart extends Case{

	private static final long serialVersionUID = 1L;
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
