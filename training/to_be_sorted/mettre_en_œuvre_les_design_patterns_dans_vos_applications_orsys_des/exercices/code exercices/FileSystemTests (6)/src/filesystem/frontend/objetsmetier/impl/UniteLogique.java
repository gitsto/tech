package filesystem.frontend.objetsmetier.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElement;
import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.ILien;
import filesystem.frontend.objetsmetier.intf.IPartition;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;
import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.IFormat;
import filesystem.shared.objetsmetier.intf.ILienHandle;
import filesystem.shared.objetsmetier.intf.IUniteLogiqueHandle;

public class UniteLogique implements IUniteLogique {

	private String nom;
	private IFormat format;
	private IUniteLogiqueHandle handle;
	private List<ChangeListener> listeners = new ArrayList<ChangeListener>(); 
	
	public UniteLogique(String nom, IPartition partition) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public int getDisponible() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDisponible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void formater(IFormat format) {
		format.formater(handle);
		this.format = format;
	}

	@Override
	public boolean isFormatee() {
		return format != null;
	}

	@Override
	public IFichierHandle creerFichier(IFichier fichier) {
		return format.creerFichier(fichier.getNom(), fichier.getTaille(), fichier.getParent().getHandle());
	}

	@Override
	public IDossierHandle creerDossier(IDossier sousDossier) {
		return format.creerDossier(sousDossier.getNom(), sousDossier.getParent().getHandle());
	}

	@Override
	public ILienHandle creerLien(ILien lien) {
		return format.creerLien(lien.getNom(), lien.getElementReferencable().getHandle(), lien.getParent().getHandle());
	}

	@Override
	public void addChangeListener(ChangeListener l) {
		listeners.add(l);
	}
	
	@Override
	public void fireStateChanged(IElement element) {
		ChangeEvent evt = new ChangeEvent(element);
		for (ChangeListener cl : listeners) {
			cl.stateChanged(evt);
		}
	}
}
