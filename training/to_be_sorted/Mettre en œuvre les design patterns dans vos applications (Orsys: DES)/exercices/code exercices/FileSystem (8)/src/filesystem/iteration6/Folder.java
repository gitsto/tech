package filesystem.iteration6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Folder extends ReferencableElement implements IFolder {
	/**
	 */
	private Map<String, IElement> elements;

	private List<FolderListener> listeners = new ArrayList<FolderListener>();

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

	public ILogicalUnit getLogicalUnit() {
		return getParentFolder().getLogicalUnit();
	}

	public int getLogicalUnitCapacity() {
		return getLogicalUnit().getCapacity();
	}

	public void addFile(IFile file) {
		checkFileSize(file);
		ILogicalUnit unit = getLogicalUnit();
		IPhysicalFile physicalFile = unit.createFile(file);
		file.setPhysicalFile(physicalFile);
		addElement(file);
		notifyFileAdded();
	}

	public void addFolder(IFolder folder) {
		addElement(folder);
	}

	public void addLink(ILink link) {
		addElement(link);
	}

	public void addFolderListener(FolderListener folderListener) {
		listeners.add(folderListener);
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

	private void notifyFileAdded() {
		FolderEvent evt = new FolderEvent(this);
		for (FolderListener listener : listeners) {
			listener.fileAdded(evt);
		}
	}

}
