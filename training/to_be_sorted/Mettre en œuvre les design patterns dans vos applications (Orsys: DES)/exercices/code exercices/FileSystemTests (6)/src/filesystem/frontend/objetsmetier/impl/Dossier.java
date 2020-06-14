/**
 * 
 */
package filesystem.frontend.objetsmetier.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElement;
import filesystem.frontend.objetsmetier.intf.IElementReferencable;
import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.ILien;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;
import filesystem.frontend.objetsmetier.intf.Visitor;
import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.ILienHandle;

public class Dossier extends ElementReferencable implements
		IDossier {


	private Map<String, IElement> elements = new HashMap<String, IElement>();
	private IDossierHandle handle;

	public Dossier(String nom) {
		super(nom);
	}

	@Override
	public int getTaille() {
		int taille = 0;
		for (IElement element : elements.values()) {
			taille += element.getTaille();
		}
		return taille;
	}

	@Override
	public IDossierHandle getHandle() {
		return handle;
	}

	@Override
	public void setHandle(IDossierHandle handle) {
		this.handle = handle;
	}

	@Override
	public void ajouterFichier(IFichier fichier) {
		ajouterElementReferencable(fichier);
		IFichierHandle handle = getUniteLogique().creerFichier(fichier);
		fichier.setHandle(handle);
	}


	@Override
	public void ajouterLien(ILien lien) {
		ajouterElement(lien);
		ILienHandle handle = getUniteLogique().creerLien(lien);
		lien.setHandle(handle);
	}

	@Override
	public void ajouterDossier(IDossier sousDossier) {
		ajouterElementReferencable(sousDossier);
		IDossierHandle handle = getUniteLogique().creerDossier(sousDossier);
		sousDossier.setHandle(handle);
	}


	@Override
	public Iterator<IElement> iterator() {
		return elements.values().iterator();
	}


	private void ajouterElementReferencable(IElementReferencable element) {
		verifierTailleDisponibile(element);
		ajouterElement(element);
	}


	private void verifierTailleDisponibile(IElementReferencable element) {
		if (element.getTaille() > getUniteLogique().getDisponible())
			throw new IllegalArgumentException();
	}


	private void verifierUnicite(IElement element) {
		if (elements.containsKey(element.getNom()))
			throw new IllegalArgumentException();
	}


	private void ajouterElement(IElement element) {
		verifierUnicite(element);
		element.setParent(this);
		elements.put(element.getNom(), element);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitDossier(this);
	}

	/**
	 * @Generated Green UML
	 */
	private List<IElement> iElement;
	/**
	 * @Generated Green UML
	 */
	private Dossier(String nom, List<IElement> iElement2) {
		super(nom);
		iElement = iElement2;
	}
}
