package monopoly.metier;

import monopoly.exception.JeSuisFaucheException;

public abstract class CasePropriete extends Case{
	private int prix;
	private Joueur proprietaire = null;
	private Groupe groupe=null;
	
	public CasePropriete(String nom, int prix, Groupe groupe) {
		super(nom);
		this.prix=prix;
		this.groupe=groupe;
		groupe.ajouterProprieteAuGroupe(this);
	}
	public void sArreterSur(Joueur joueur)throws JeSuisFaucheException {
		if(proprietaire == null){
			if(joueur.proposerAcheter()){
				joueur.debiter(prix);
				this.setProprietaire(joueur);
			}
		}else{
			if(proprietaire != joueur){
				int montant = calculerLoyer();
				joueur.debiter(montant);
				proprietaire.crediter(montant);
			}
		}
	}
	
	private void setProprietaire(Joueur joueur) {
		proprietaire = joueur;
	}
	
	public abstract int calculerLoyer();
	
	public int getPrix() {
		return prix;
	}
	public Joueur getPropio() {
		return proprietaire;
	}
	public Groupe getGroupe(){
		return groupe;
	}
}
