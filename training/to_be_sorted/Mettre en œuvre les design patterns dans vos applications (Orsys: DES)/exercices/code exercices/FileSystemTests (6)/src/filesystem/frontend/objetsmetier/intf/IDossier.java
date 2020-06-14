package filesystem.frontend.objetsmetier.intf;

import filesystem.shared.objetsmetier.intf.IDossierHandle;

public interface IDossier extends IElementReferencable, Iterable<IElement> {

	void ajouterFichier(IFichier fichier);

	void ajouterLien(ILien lien);

	void ajouterDossier(IDossier sousDossier);

	void setHandle(IDossierHandle handle);
	IDossierHandle getHandle();
}
