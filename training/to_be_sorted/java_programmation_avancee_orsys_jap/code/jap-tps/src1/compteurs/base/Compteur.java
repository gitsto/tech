package compteurs.base;


public class Compteur implements ICompteur {

	private int valeur;
	
	public Compteur(int v)  { valeur = v; }
	public Compteur() { this(0); }

	public int getValeur() { return valeur; }
	public void incrementer() { incrementer(1);}
	public void incrementer(int v) { valeur += v; }
}
