package monopoly.metier;

public class De {
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
}
