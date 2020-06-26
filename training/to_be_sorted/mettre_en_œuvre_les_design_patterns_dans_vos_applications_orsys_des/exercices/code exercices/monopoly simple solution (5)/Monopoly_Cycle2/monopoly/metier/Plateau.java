package monopoly.metier;

public class Plateau {
	private Case[] cases = new Case[40];
	
	public Plateau() {
		//Initialisation du tableau de cases
		for(int i=0;i<40;i++){
			switch(i){
				case 0:
					cases[i] = new CaseDepart("Case départ");
					break;
				case 4:
					cases[i] = new CaseImpot("Case impôt");
					break;
				case 38:
					cases[i] = new CaseTaxeDeLuxe("Case taxe de luxe");
					break;
				default :
					cases[i]=new Case("Case "+i);
					break;
			}
			
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
