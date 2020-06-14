package filesystem.iteration3;

import java.util.Iterator;

public interface IFolder extends IReferenceableElement, Iterable<IElement> {
	void addFile(IFile fichier);
	void addFolder(IFolder dossier);
	IElement getElementByName(String nom);
	int getLogicalUnitCapacity();
	void accept(Visitor vistor);
	Iterator<IElement> iterator();
}