package monopoly.metier;

public class Gobelet {

	//Attributs 
	private Lancable de1 = new De();
	private Lancable de2 = new De();
	
	//Constructeur
	private Gobelet(){
	}
	
	private static Gobelet instance = null;
	public static Gobelet getInstance() {
		if(instance == null){
			instance = new Gobelet();
		}
		return instance;
	}
	
	public void lancer(){
		de1.lancer();
		de2.lancer();
	}
	
	public int getValeurFace(){
		return de1.getValeurFace() + de2.getValeurFace();
	}
	
	public boolean aFaitDouble(){
		return de1.equals(de2);
	}

	
	
}
