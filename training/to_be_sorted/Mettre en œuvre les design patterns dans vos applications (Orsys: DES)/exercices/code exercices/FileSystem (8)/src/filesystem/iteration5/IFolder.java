package filesystem.iteration5;

import java.util.Iterator;

public interface IFolder extends IReferenceableElement, Iterable<IElement> {
	void addFile(IFile fichier);
	void addFolder(IFolder dossier);
	IElement getElementByName(String nom);
	ILogicalUnit getLogicalUnit();
	int getLogicalUnitCapacity();
	void accept(Visitor vistor);
	Iterator<IElement> iterator();
	void addFolderListener(FolderListener dossierListener);
}