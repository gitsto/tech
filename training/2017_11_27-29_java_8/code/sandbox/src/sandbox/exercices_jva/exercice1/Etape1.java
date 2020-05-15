package sandbox.exercices_jva.exercice1;

import java.util.Arrays;

public class Etape1 {

	Runnable r = () -> System.out.println(this);

	public static void main(String[] args) {
		Etape1 etape1 = new Etape1();
		etape1.r.run();

		String[] stagiaires = { "Emmanuel", "Stefan", "Mohand", "Pierre", "Trang" };
		Arrays.sort(stagiaires, (s1,s2)->s1.compareTo(s2));
//		Arrays.asList(stagiaires).forEach((stagiaire -> s1.compareTo(s2)));
	}

}
