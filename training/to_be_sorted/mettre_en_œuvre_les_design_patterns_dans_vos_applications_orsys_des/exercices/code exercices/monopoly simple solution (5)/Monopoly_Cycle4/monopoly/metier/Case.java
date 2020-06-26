package monopoly.metier;

import monopoly.exception.JeSuisFaucheException;

public class Case {

	private String nom;
	private Case suivante;
	
	public Case(String nom){
		this.nom = nom;
	}

	public void survoler(Joueur joueur){
		
	}
	public void sArreterSur(Joueur joueur) throws JeSuisFaucheException{
		
	}
	public Case getSuivante(){
		return suivante;
	}
	public void setSuivante(Case suivante){
		this.suivante = suivante;
	}
	public String getNom(){
		return nom;
	}
	

	
}
