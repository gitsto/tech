package monopoly.metier;

import java.io.Serializable;

import monopoly.exception.JeSuisFaucheException;

public class Case implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
