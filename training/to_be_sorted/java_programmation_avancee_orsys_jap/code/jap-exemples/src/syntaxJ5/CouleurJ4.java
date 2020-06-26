package syntaxJ5;

public class CouleurJ4 implements java.io.Serializable { 
	private static final long serialVersionUID = -3396537866397086728L;
	
	public static final CouleurJ4 BLEU = new CouleurJ4("BLEU", 0);
	public static final CouleurJ4  BLANC = new CouleurJ4("BLANC", 1);
	public static final CouleurJ4 ROUGE = new CouleurJ4("ROUGE", 2);
	public static final CouleurJ4 JAUNE = new CouleurJ4("JAUNE", 3);
	public static final CouleurJ4 VERT = new CouleurJ4("VERT", 4);
	
	private String name;
	private int ordinal;
	
	private CouleurJ4(String n, int o) { name = n; ordinal = o; }
	public String toString() { return name; }
	public int getOrdinal() { return ordinal; }
	
	/*public Object readResolve() {
		switch(getOrdinal()) {
		case 0 : return BLEU;
		case 1 : return BLANC;
		case 2 : return ROUGE;
		case 3 : return JAUNE;
		case 4 : return VERT;
		default : return null;
		}
	}*/
}
