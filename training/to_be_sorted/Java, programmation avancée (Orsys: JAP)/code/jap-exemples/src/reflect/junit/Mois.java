package reflect.junit;

import java.util.GregorianCalendar;
 
public class Mois {
	public static final String[] tableMois = {
		"factice", 
		"de janvier", 
		"de f�vrier",
		"de mars", 
		"d'avril", 
		"de mai", 
		"de juin", 
		"de juillet", 
		"d'ao�t",
		"de septembre", 
		"d'octobre", 
		"de novembre", 
		"de d�cembre" };
	private int mois;
	private Annee annee;

	public Mois(int mois, int annee) {
		if (mois < 1 || mois > 12) {
			throw new IllegalArgumentException(mois + " incorrect pour un mois");
		}
		this.mois = mois;
		this.annee = new Annee(annee);
	}

	public Mois(int jour) {
		this(jour, new GregorianCalendar().get(GregorianCalendar.YEAR));
	}

	public int nombreJours() {
		switch (mois) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		default:
			return annee.isBissextile() ? 29 : 28;
		}
	}

	public String toString() {
		return "mois " + tableMois[mois] + " de l'ann�e " + annee;
	}
}
