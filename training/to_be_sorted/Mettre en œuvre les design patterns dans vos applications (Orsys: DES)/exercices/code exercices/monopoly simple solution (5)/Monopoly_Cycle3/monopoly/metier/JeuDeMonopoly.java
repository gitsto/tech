package monopoly.metier;

import java.util.ArrayList;
import java.util.List;

public class JeuDeMonopoly {
 
	private List<Joueur> joueurs;
	private int nbTours; 
	
	public JeuDeMonopoly(int nbJoueurs, int nbTours) {
		this.nbTours = nbTours;
		Plateau plateau = new Plateau();
		Case caseDepart = plateau.getCaseDepart();
		//this.joueurs = new Joueur[nbJoueurs];
		this.joueurs = new ArrayList<Joueur>();
		
		//Initialisation du tableau de joueurs
		for(int i=0;i<nbJoueurs;i++){
			joueurs.add(new Joueur(getPion(), caseDepart));
		}
	}
	
	public void jouer(){
		for(int i=0;i<nbTours;i++){
			for(int j=0;j<joueurs.size();j++){
				Joueur joueur = joueurs.get(j);
				joueur.aTonTour();
			}
			System.out.println();
		}
	}
	
	
	private String[] pions = new String[]{"Chien","Fer à repasser", "Navire", "Canon", "Haut de forme", "Cheval", "Chaussure", "Voiture"};
	/** 
	 * @return nom du pion
	 */
	private String getPion(){
		int indice =(int)(Math.random()*pions.length);
		String nomDuPion = pions[indice];
		String[] temp = new String[pions.length-1];
		for(int i=0;i<temp.length;i++){
			if(i==indice)
				temp[i]=pions[pions.length-1];
			else
				temp[i]=pions[i];
		}
		pions=temp;
		return nomDuPion;
	}
	
	
	
	
}
