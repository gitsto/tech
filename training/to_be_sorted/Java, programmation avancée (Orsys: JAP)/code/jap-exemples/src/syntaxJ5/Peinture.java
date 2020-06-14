package syntaxJ5;

import java.io.Serializable;

public class Peinture implements Serializable {

	private static final long serialVersionUID = -6472424259061400095L;
	
	private Couleur couleur;
	
	public Peinture(Couleur c) { couleur = c; }
	public Couleur getCouleur() { return couleur; }
}
