package filesystem.shared.objetsmetier.intf;

import java.util.EventObject;

public class FichierHandleEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private String nom;
	private int taille;

	public FichierHandleEvent(Object source, String nom, int taille) {
		super(source);
		this.nom =  nom;
		this.taille = taille;
	}

	public String getNom() {
		return nom;
	}

	public int getTaille() {
		return taille;
	}

}
