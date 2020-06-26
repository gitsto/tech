package filesystem.frontend.objetsmetier.intf;

import javax.swing.event.ChangeListener;

import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.IFormat;
import filesystem.shared.objetsmetier.intf.ILienHandle;

public interface IUniteLogique {
	String getNom();

	void setNom(String nom);

	int getDisponible();

	void setDisponible();

	void formater(IFormat format);

	boolean isFormatee();

	IFichierHandle creerFichier(IFichier fichier);

	ILienHandle creerLien(ILien lien);

	IDossierHandle creerDossier(IDossier sousDossier);

	void addChangeListener(ChangeListener l);

	void fireStateChanged(IElement element);
}
