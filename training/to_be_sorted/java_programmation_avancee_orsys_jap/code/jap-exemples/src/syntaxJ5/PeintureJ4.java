package syntaxJ5;

import java.io.Serializable;

public class PeintureJ4 implements Serializable {

	private static final long serialVersionUID = -6472424259061400096L;
	
	private CouleurJ4 couleur;
	
	public PeintureJ4(CouleurJ4 c) { couleur = c; }
	public CouleurJ4 getCouleur() { return couleur; }
}
