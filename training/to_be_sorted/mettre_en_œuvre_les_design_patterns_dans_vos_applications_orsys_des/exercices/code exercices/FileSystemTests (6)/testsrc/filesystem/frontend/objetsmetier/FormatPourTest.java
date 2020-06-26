package filesystem.frontend.objetsmetier;

import filesystem.shared.objetsmetier.intf.FichierHandleListener;
import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IElementReferencableHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.IFormat;
import filesystem.shared.objetsmetier.intf.ILienHandle;
import filesystem.shared.objetsmetier.intf.IUniteLogiqueHandle;

public class FormatPourTest implements IFormat {

	@Override
	public void formater(IUniteLogiqueHandle uniteLogique) {
	}

	@Override
	public IFichierHandle creerFichier(String nom, int taille,
			IDossierHandle dossierParent) {
		return new IFichierHandle() {
			@Override
			public void addFichierListener(FichierHandleListener l) {
			}
		};
	}

	@Override
	public IDossierHandle creerDossier(String nom, IDossierHandle handle) {
		return new IDossierHandle() {
		};
	}

	@Override
	public ILienHandle creerLien(String nom,
			IElementReferencableHandle reference, IDossierHandle dossierParent) {
		return new ILienHandle() {
		};
	}

}
