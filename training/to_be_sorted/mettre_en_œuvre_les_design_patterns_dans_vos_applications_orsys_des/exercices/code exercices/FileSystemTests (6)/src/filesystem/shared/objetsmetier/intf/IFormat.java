package filesystem.shared.objetsmetier.intf;

public interface IFormat {
	void formater(IUniteLogiqueHandle uniteLogique);

	IFichierHandle creerFichier(String nom, int taille,
			IDossierHandle dossierParent);

	IDossierHandle creerDossier(String nom, IDossierHandle handle);

	ILienHandle creerLien(String nom, IElementReferencableHandle reference,
			IDossierHandle dossierParent);
}
