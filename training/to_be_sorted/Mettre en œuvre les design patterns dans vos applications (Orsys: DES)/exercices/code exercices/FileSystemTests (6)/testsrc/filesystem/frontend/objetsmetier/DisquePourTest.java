/**
 * 
 */
package filesystem.frontend.objetsmetier;

import filesystem.frontend.objetsmetier.impl.Disque;
import filesystem.frontend.objetsmetier.impl.UniteLogique;
import filesystem.frontend.objetsmetier.intf.IPartition;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;
import filesystem.shared.objetsmetier.intf.IFormat;

public class DisquePourTest extends Disque {
	public static final String FORMAT = "LE_FORMAT";

	char dernierAffecte = 'A' - 1;

	public DisquePourTest() {
	}

	@Override
	public IUniteLogique creerUniteLogique(IPartition partition) {
		dernierAffecte++;
		IUniteLogique ul = new UniteLogique(getDernierNomAffecte(), partition);
		return ul;
	}

	@Override
	public String getDernierNomAffecte() {
		return "" + dernierAffecte;
	}

	@Override
	public void format(IUniteLogique uniteLogique, String format) {
		if (FORMAT.equals(format)) {
			IFormat f = new FormatPourTest();
			uniteLogique.formater(f);
		}
	}
}