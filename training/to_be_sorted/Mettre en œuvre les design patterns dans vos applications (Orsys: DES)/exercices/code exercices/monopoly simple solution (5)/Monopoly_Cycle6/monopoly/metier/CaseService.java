package monopoly.metier;

public class CaseService extends CasePropriete{

	
	public CaseService(String nom, int prix, Groupe groupe) {
		super(nom, prix, groupe);
	}
	
	public int calculerLoyer() {
		if(getGroupe().isMonopoly(getPropio())){
			return Gobelet.getInstance().getValeurFace() * 10;
		}else{
			return Gobelet.getInstance().getValeurFace() * 4;
		}
	}
}
