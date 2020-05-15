package fr.orsys.jva.exercice1;

public class Etape4 {
	public static void main(String[] args) {
		Defaulable d1	= DefaulableFactory.create(DefaulableImpl::new);
		System.out.println(d1.notRequired());
		Defaulable d2	= DefaulableFactory.create(OverridableImpl::new);
		System.out.println(d2.notRequired());
	}
}
