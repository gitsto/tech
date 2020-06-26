package monopoly.metier;

public class CaseLot extends CasePropriete{

	private int loyer;
	
	public CaseLot(String nom, int prix, int position) {
		super(nom, prix);
		this.loyer=40+position*10;
	}
	
	public int calculerLoyer() {
		return loyer;
	}
}
