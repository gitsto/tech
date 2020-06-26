package monopoly.metier;

public class CaseService extends CasePropriete{

	
	public CaseService(String nom, int prix) {
		super(nom, prix);
	}
	
	public int calculerLoyer() {
		return Gobelet.getInstance().getValeurFace() * 4;
	}
}
