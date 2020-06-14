package syntaxJ5;

public class TestAutoboxing {

	public static void main(String[] args) {
		Integer i0 = new Integer(1);
		Integer i1 = new Integer(1);
		int i2 = 1;
		if (i0 == i1)  System.out.println("i0 == i1"); 
		else  System.out.println("i0 != i1"); 
		if (i0 == 1)  System.out.println("i0 == 1"); 
		else  System.out.println("i0 != 1"); 
		if (i0 == i2)  System.out.println("i0 == i2"); 
		else System.out.println("i0 != i2"); 
		Integer j0 = 130;
		Integer j1 =  130;
		if (j0 == j1)  System.out.println("j0 == j1");
		else System.out.println("j0 != j1"); 
		if (j0 == 130)  System.out.println("j0 == 130");
		else System.out.println("j0 != 130"); 
		Integer k0 = Integer.valueOf(130);
		Integer k1 =  Integer.valueOf(130);
		if (k0 == k1)  System.out.println("k0 == k1");
		else System.out.println("k0 != k1"); 

		
	}

}
