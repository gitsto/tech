package monopoly.metier;

public class Plateau {
	private Case[] cases = new Case[40];
	
	public Plateau() {
		//Initialisation du tableau de cases
		cases[0] = new Case("Départ");
		for(int i=1;i<40;i++){
			cases[i]=new Case("Case "+i);
		}
		
		//Chainage des cases
		for(int i=0;i<39;i++){
			cases[i].setSuivante(cases[i+1]);
		}
		cases[39].setSuivante(cases[0]);
	}
	
	public Case getCaseDepart(){
		return cases[0];
	}

}
