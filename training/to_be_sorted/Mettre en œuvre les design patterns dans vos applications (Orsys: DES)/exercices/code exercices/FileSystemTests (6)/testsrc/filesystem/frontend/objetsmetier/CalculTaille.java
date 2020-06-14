package filesystem.frontend.objetsmetier;

import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElement;
import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.Visitor;

class CalculTaille extends Visitor {
	int taille;
	@Override
	public void visitDossier(IDossier dossier) {
		for (IElement element : dossier) {
			element.accept(this);
		}
	}
	@Override
	public void visitFichier(IFichier fichier) {
		taille += fichier.getTaille();
	}
}
