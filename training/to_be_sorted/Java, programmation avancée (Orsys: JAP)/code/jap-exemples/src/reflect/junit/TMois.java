package reflect.junit;


public class TMois {
	
	public void test0() throws MyJUNITException {
		Mois mois = new Mois(2,2004);
		if(29 != mois.nombreJours()) MyJUNIT.fail("le nombre de mois devrait etre 29");
	}
	
	public void test1() throws MyJUNITException {
		Mois mois = new Mois(2,2005);
		if(29 != mois.nombreJours()) MyJUNIT.fail("le nombre de mois devrait etre 28");
	}

}
