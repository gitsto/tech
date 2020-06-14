package classLoader;

public class TestAbis {
	public static final String key = "4";

	public static void main(String[] args) {
		IA refA = null;
		System.err.println("debut de l'execution");
		try {
			if (key.equals("1")) refA = (IA) Class.forName("classLoader.A1").newInstance();
			else if (key.equals("2")) refA = (IA) Class.forName("classLoader.A2").newInstance();
			else if (key.equals("3")) refA = (IA) Class.forName("classLoader.A3").newInstance();
			else if (key.equals("4")) refA = (IA) Class.forName("classLoader.A4").newInstance();
			if (refA != null) System.err.println("refA : " + refA.getClass());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
