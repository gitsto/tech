/**
 * 
 */
package filesystem.frontend.objetsmetier.impl;

import filesystem.frontend.objetsmetier.intf.IUniteLogique;

/**
 * @author daniel.rosenblatt
 * 
 */
public class DossierRacine extends Dossier {

	private IUniteLogique uniteLogique;

	/**
	 * @param nom
	 */
	public DossierRacine(String nom, IUniteLogique uniteLogique) {
		super(nom);
		this.uniteLogique = uniteLogique;
	}

	@Override
	public IUniteLogique getUniteLogique() {
		return uniteLogique;
	}
}
