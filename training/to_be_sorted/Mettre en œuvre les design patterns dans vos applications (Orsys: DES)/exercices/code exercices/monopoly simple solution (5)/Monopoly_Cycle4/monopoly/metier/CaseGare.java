package monopoly.metier;

public class CaseGare extends CasePropriete {

	public CaseGare (String nom, int prix) {
		super(nom, prix);
	}
	
	public int calculerLoyer() {
		return 25;
	}
}
