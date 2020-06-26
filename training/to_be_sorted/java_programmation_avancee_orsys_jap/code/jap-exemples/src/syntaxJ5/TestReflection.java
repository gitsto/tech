package syntaxJ5;

import java.lang.reflect.Field;

public class TestReflection {

	public static void main(String[] args) {
		Peinture peinture = new Peinture(Couleur.JAUNE);
		Class<Peinture> clz = Peinture.class;
		Field fld = null;
		try {
			fld = clz.getDeclaredField("couleur");
			try {
				fld.set(peinture, Couleur.BLEU);
				System.out.println("[1] la couleur de la peinture est " + peinture.getCouleur());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			fld.setAccessible(true);
			try {
				fld.set(peinture, Couleur.VERT);
				System.out.println("[2] la couleur de la peinture est " + peinture.getCouleur());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
