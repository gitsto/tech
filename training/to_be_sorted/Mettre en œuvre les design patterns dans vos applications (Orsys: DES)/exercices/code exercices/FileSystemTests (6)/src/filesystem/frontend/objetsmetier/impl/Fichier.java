package filesystem.frontend.objetsmetier.impl;

import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.Visitor;
import filesystem.shared.objetsmetier.intf.FichierHandleEvent;
import filesystem.shared.objetsmetier.intf.FichierHandleListener;
import filesystem.shared.objetsmetier.intf.IFichierHandle;

public class Fichier extends ElementReferencable implements IFichier, FichierHandleListener {

	private int taille;
	private IFichierHandle handle;
	
	public Fichier(String nom, int taille) {
		super(nom);
		this.taille = taille;
	}

	@Override
	public int getTaille() {
		return taille;
	}

	@Override
	public void setTaille(int taille) {
		this.taille = taille;
	}

	@Override
	public IFichierHandle getHandle() {
		return handle;
	}

	@Override
	public void setHandle(IFichierHandle handle) {
		this.handle = handle;
		handle.addFichierListener(this);
	}

	@Override
	public void tailleChangee(FichierHandleEvent evt) {
		taille = evt.getTaille();
		getUniteLogique().fireStateChanged(this);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFichier(this);
	}

}
