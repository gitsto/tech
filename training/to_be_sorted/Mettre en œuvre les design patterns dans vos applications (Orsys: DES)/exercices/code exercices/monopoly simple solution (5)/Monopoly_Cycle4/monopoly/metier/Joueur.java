package monopoly.metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import monopoly.exception.JeSuisFaucheException;

public class Joueur {

	//Attributs
	private String nom;
	private Case position;
	private int solde=1500;
	
	//Constructeur
	public Joueur(String nom, Case depart) {
		this.nom = nom;
		this.position=depart;
	}
	
	public void aTonTour() throws JeSuisFaucheException{
		Gobelet gobelet = Gobelet.getInstance();
		gobelet.lancer();
		int score = gobelet.getValeurFace();
		for(int i=0;i<score-1;i++){
			position = position.getSuivante();
			position.survoler(this);
		}
		position = position.getSuivante();
		System.out.println(this.nom+" a fait "+score);
		System.out.println(this.nom+" atterit sur la "+position.getNom());

		position.sArreterSur(this);

		if(gobelet.aFaitDouble()){
			System.out.println("Double : Rejouer !");
			this.aTonTour();
		}
		System.out.println(this.nom+" a "+this.solde);
	}

	public void crediter(int mnt) {
		this.solde += mnt;	
	}
	public void debiter(int mnt) throws JeSuisFaucheException {
		if(mnt > this.solde)
			throw new JeSuisFaucheException("Le joueur "+this.nom+" est fauché");
		this.solde -= mnt;	
	}
	public int getSolde(){
		return solde;
	}

	public boolean proposerAcheter() {
		boolean resultat = false;
		if(position instanceof CasePropriete){
			CasePropriete propriété = (CasePropriete)position;
			if(propriété.getPrix()<solde){
				System.out.println(this.nom+" achète "+propriété.getNom()+" pour "+propriété.getPrix()+" ? (o/n)");
				resultat = true;
			}
		}	
		return resultat;
	}
}
