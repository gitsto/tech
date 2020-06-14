package monopoly.metier;

public class CaseGare extends CasePropriete {

	public CaseGare (String nom, int prix, Groupe groupe) {
		super(nom, prix, groupe);
	}
	
	public int calculerLoyer() {
		int loyer=0;
		int nbProp = getGroupe().compteProprietesParJoueur(getPropio());
		switch (nbProp) {
			case 1:
				loyer=25;
				break;
			case 2:
				loyer=50;
				break;
			case 3:
				loyer=100;
				break;
			default:
				loyer=200;
				break;
			}
		return loyer;
	}
}
