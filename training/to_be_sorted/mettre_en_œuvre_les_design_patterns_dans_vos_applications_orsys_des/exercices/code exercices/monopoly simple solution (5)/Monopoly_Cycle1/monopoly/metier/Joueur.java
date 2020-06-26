package monopoly.metier;

public class Joueur {

	//Attributs
	private String nom;
	private Case position;
	
	//Constructeur
	public Joueur(String nom, Case depart) {
		this.nom = nom;
		this.position=depart;
	}
	
	public void aTonTour(Gobelet gobelet){
		gobelet.lancer();
		int score = gobelet.getValeurFace();
		for(int i=0;i<score;i++){
			position = position.getSuivante();
		}
		
		System.out.println(this.nom+" a fait "+score);
		System.out.println(this.nom+" atterit sur la "+position.getNom());
	}
	
}
