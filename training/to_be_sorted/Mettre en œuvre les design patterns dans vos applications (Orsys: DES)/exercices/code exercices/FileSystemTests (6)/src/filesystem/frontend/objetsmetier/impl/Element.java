/**
 * 
 */
package filesystem.frontend.objetsmetier.impl;

import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElement;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;

/**
 * @author daniel.rosenblatt
 *
 */
public abstract class Element implements IElement {

	private String nom;
	private IDossier parent;

	public Element(String nom) {
		this.nom = nom;
	}

	/* (non-Javadoc)
	 * @see filesystem.frontend.intf.IElement#getNom()
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/* (non-Javadoc)
	 * @see filesystem.frontend.intf.IElement#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String nom) {
		this.nom =  nom;
	}

	@Override
	public IDossier getParent() {
		return parent;
	}

	@Override
	public void setParent(IDossier parent) {
		this.parent = parent;
	}

	@Override
	public IUniteLogique getUniteLogique() {
		return getParent().getUniteLogique();
	}
}
