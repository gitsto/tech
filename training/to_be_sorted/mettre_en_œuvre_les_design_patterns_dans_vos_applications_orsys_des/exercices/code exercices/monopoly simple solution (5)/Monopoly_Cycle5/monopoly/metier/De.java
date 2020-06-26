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
	 * @return la valeur de la face du d�
	 */
	public int getValeurFace(){
		return valeurFace;
	}
	/**
	 * Effectue un tirage all�atoire entre 1 et 6
	 * et affecte cette valeur � l'attribut valeurFace
	 */
	public void lancer(){
		valeurFace = (int)(Math.random()*6)+1;
	}
	
	//R�d�finition des m�thodes toString et equals
	public String toString() {
		return "La valeur de la face du d� est "+valeurFace;
	}
	public boolean equals(Object obj) {
		if(obj instanceof De){
			De d2 = (De)obj;
			return (valeurFace == d2.getValeurFace());
		}
		return false;
	}
}
