package monopoly.metier;

public class Joueur {

	//Attributs
	private String nom;
	private Case position;
	private float solde=1500;
	
	//Constructeur
	public Joueur(String nom, Case depart) {
		this.nom = nom;
		this.position=depart;
	}
	
	public void aTonTour(Gobelet gobelet){
		gobelet.lancer();
		int score = gobelet.getValeurFace();
		for(int i=0;i<score-1;i++){
			position = position.getSuivante();
			position.survoler(this);
		}
		position = position.getSuivante();
		position.sArreterSur(this);
		
		System.out.println(this.nom+" a fait "+score);
		System.out.println(this.nom+" atterit sur la "+position.getNom());
		
		if(gobelet.aFaitDouble()){
			this.aTonTour(gobelet);
		}
	}

	public void crediter(float mnt) {
		this.solde += mnt;	
	}
	public void debiter(float mnt) {
		this.solde -= mnt;	
	}
	public float getSolde(){
		return solde;
	}
}
