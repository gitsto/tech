package monopoly.metier;

public class Gobelet {

	//Attributs 
	private De de1 = new De();
	private De de2 = new De();
	
	//Constructeur
	public Gobelet(){
	}
	
	public void lancer(){
		de1.lancer();
		de2.lancer();
	}
	
	public int getValeurFace(){
		return de1.getValeurFace() + de2.getValeurFace();
	}
	
}
