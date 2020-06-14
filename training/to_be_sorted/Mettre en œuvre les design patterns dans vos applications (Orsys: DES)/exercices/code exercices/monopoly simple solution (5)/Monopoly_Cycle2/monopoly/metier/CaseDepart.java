package monopoly.metier;

public class CaseDepart extends Case{

	public CaseDepart(String nom){
		super(nom);
	}
	
	public void sArreterSur(Joueur joueur) {
		joueur.crediter(400);
	}
	
	
	public void survoler(Joueur joueur) {
		joueur.crediter(200);
	}
}
