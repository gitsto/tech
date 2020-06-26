package reflect.junit;

public class Annee {
	private int annee;

	public Annee(int annee) {
		if (annee < 0) {
			throw new IllegalArgumentException(annee
					+ " incorrect pour une ann�e");
		}
		this.annee = annee;
	}
	
	public boolean isBissextile() {
		if (annee%4!=0) return false;
		if (annee%400==0) return true;
		if (annee%100==0) return false;
		return true;
	}

	public String toString() {
		return ""+annee;
	}
}
