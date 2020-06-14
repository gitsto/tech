package monopoly.metier;

import monopoly.exception.JeSuisFaucheException;

public class CaseImpot extends Case{

	public CaseImpot(String nom) {
		super(nom);
	}
	
	public void sArreterSur(Joueur joueur) throws JeSuisFaucheException{
		int mnt = (int)(joueur.getSolde()*0.1);
		joueur.debiter(Math.max(200, mnt));
	}
}
