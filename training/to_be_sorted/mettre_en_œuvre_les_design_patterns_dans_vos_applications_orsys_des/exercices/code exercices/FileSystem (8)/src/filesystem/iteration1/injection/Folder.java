package filesystem.iteration1.injection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Folder extends ReferencableElement implements IFolder {
	private Map<String, IElement> elements = new HashMap<String, IElement>();
	
	public Folder(String nom) {
		super(nom);
	}

	public int getSize() {
		int taille = 0;
		for (IElement element : elements.values()) {
			taille += element.getSize();
		}
		return taille;
	}

	public IElement getElementByName(String nom) {
		return elements.get(nom);
	}

	public int getLogicalUnitCapacity() {
		return getParentFolder().getLogicalUnitCapacity();
	}

	public void addFile(IFile file) {
		checkFileSize(file);
		addElement(file);
	}

	public void addFolder(IFolder folder) {
		addElement(folder);
	}

	public void addLink(ILink link) {
		addElement(link);
	}

	@Override
	public Iterator<IElement> iterator() {
		return elements.values().iterator();
	}

	private void checkFileSize(IFile file) {
		if (file.getSize() < 0)
			throw new IllegalArgumentException("negative size");
		if (file.getSize() > getLogicalUnitCapacity())
			throw new IllegalArgumentException("insufficent capacity");
	}

	private void addElement(IElement element) {
		if (elements.containsKey(element.getName()))
			throw new IllegalArgumentException("duplicated name");
		elements.put(element.getName(), element);
		element.setParentFolder(this);
	}

}
