package monopoly.metier;

public class JeuDeMonopoly {
 
	private Joueur[] joueurs;
	private Gobelet gobelet;
	private int nbTours; 
	
	public JeuDeMonopoly(int nbJoueurs, int nbTours) {
		this.nbTours = nbTours;
		Plateau plateau = new Plateau();
		this.gobelet = new Gobelet();
		Case caseDepart = plateau.getCaseDepart();
		this.joueurs = new Joueur[nbJoueurs];
		
		//Initialisation du tableau de joueurs
		for(int i=0;i<nbJoueurs;i++){
			joueurs[i]=new Joueur(getPion(), caseDepart);
		}
	}
	
	public void jouer(){
		for(int i=0;i<nbTours;i++){
			for(int j=0;j<joueurs.length;j++){
				joueurs[j].aTonTour(gobelet);
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
