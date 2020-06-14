package monopoly.metier;

public class CaseTaxeDeLuxe extends Case{

	public CaseTaxeDeLuxe(String nom) {
		super(nom);
	}
	public void sArreterSur(Joueur joueur) {
		joueur.debiter(75);
	}
}
