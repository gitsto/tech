package monopoly.metier;

public class CaseLot extends CasePropriete{

	private int loyer;
	
	public CaseLot(String nom, int prix, int position, Groupe groupe) {
		super(nom, prix, groupe);
		this.loyer=40+position*10;
	}
	
	public int calculerLoyer() {
		if(getGroupe().isMonopoly(getPropio())){
			return loyer*2;
		}
		return loyer;
	}
}
