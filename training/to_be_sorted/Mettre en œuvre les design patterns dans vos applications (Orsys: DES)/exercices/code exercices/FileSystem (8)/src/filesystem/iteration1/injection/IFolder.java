package filesystem.iteration1.injection;

import java.util.Iterator;

public interface IFolder extends IReferenceableElement, Iterable<IElement> {
	void addFile(IFile fichier);
	void addFolder(IFolder dossier);
	IElement getElementByName(String nom);
	int getLogicalUnitCapacity();
	Iterator<IElement> iterator();
}