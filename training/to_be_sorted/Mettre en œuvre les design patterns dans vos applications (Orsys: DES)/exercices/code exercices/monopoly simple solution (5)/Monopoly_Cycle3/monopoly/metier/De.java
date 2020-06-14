package monopoly.metier;

public class De implements Lancable{
	//Attribut
	private int valeurFace=1;
	
	//Constructeurs
	public De(){
		this.lancer();
	}
	public De(int valeurFace){
		this.valeurFace=valeurFace;	
	}
	
	/**
	 * @return la valeur de la face du dé
	 */
	public int getValeurFace(){
		return valeurFace;
	}
	/**
	 * Effectue un tirage alléatoire entre 1 et 6
	 * et affecte cette valeur à l'attribut valeurFace
	 */
	public void lancer(){
		valeurFace = (int)(Math.random()*6)+1;
	}
	
	//Rédéfinition des méthodes toString et equals
	public String toString() {
		return "La valeur de la face du dé est "+valeurFace;
	}
	public boolean equals(Object obj) {
		if(obj instanceof De){
			De d2 = (De)obj;
			return (valeurFace == d2.getValeurFace());
		}
		return false;
	}
}
