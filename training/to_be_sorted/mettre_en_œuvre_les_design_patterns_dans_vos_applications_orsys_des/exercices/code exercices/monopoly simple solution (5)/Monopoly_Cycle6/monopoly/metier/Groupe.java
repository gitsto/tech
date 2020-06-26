package monopoly.metier;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Groupe {

	private String nom;
	private Set<CasePropriete> proprietes;
	
	public Groupe(String nom) {
		this.nom = nom;
		proprietes = new HashSet<CasePropriete>();
	}
	
	public void ajouterProprieteAuGroupe(CasePropriete caseP){
		proprietes.add(caseP);
	}
	
	public int compteProprietesParJoueur(Joueur joueur){
		int res = 0;
		if(joueur!=null){
			Iterator<CasePropriete> iterProp = proprietes.iterator();
			while(iterProp.hasNext()){
				CasePropriete currentProp = iterProp.next();
				if((currentProp!=null) && (joueur).equals(currentProp.getPropio()))
					res++;
			}
		}
		return res;
	}

	
	public boolean isMonopoly(Joueur propio) {
		return proprietes.size() == compteProprietesParJoueur(propio);
	}
	
}
