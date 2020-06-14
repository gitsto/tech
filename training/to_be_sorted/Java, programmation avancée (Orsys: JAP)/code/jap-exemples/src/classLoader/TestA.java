package classLoader;

public class TestA {
	public static String key = "4";

	public static void main(String[] args) {
		IA refA = null;
		System.err.println("debut de l'execution");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (key.equals("1")) refA = new A1();
		else if (key.equals("3")) refA = new A3();
		else if (key.equals("2")) refA = new A2();
		else if (key.equals("4")) refA = new A4();
		System.err.println("refA : " + refA.getClass());

	}

}
