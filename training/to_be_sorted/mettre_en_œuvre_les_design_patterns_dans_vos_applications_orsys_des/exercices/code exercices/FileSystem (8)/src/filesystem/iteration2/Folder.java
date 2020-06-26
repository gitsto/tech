package filesystem.iteration2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Folder extends ReferencableElement implements IFolder {
	/**
	 */
	private Map<String, IElement> elements = new HashMap<String, IElement>();

	public Folder(String name) {
		super(name);
	}

	public int getSize() {
		int taille = 0;
		for (IElement element : elements.values()) {
			taille += element.getSize();
		}
		return taille;
	}

	public IElement getElementByName(String name) {
		return elements.get(name);
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

	public Iterator<IElement> iteratorInDepthFirst() {
		List<IElement> list = new ArrayList<IElement>();
		accumulate(list);
		return list.iterator();
	}

	public void accumulate(List<IElement> list) {
		list.add(this);
		for (IElement element : elements.values()) {
			element.accumulate(list);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFolder(this);
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
