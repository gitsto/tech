package filesystem.iteration1.encapsulation;

import java.util.HashMap;
import java.util.Map;

public class Folder extends ReferencableElement {
	private Map<String, Element> elements = new HashMap<String, Element>();
	
	public Folder(String nom) {
		super(nom);
	}

	public int getSize() {
		int taille = 0;
		for (Element element : elements.values()) {
			taille += element.getSize();
		}
		return taille;
	}

	public Element getElementByName(String nom) {
		return elements.get(nom);
	}

	public int getLogicalUnitCapacity() {
		return getParentFolder().getLogicalUnitCapacity();
	}

	public void addFile(String name, int size) {
		File file = new File(name, size);
		checkFileSize(file);
		addElement(file);
	}

	public void addFolder(String name) {
		Folder folder = new Folder(name);
		addElement(folder);
	}

	public void addLink(String name, ReferencableElement ref) {
		Link link = new Link(name , ref);
		addElement(link);
	}

	private void checkFileSize(File file) {
		if (file.getSize() < 0)
			throw new IllegalArgumentException("negative size");
		if (file.getSize() > getLogicalUnitCapacity())
			throw new IllegalArgumentException("insufficent capacity");
	}

	private void addElement(Element element) {
		if (elements.containsKey(element.getName()))
			throw new IllegalArgumentException("duplicated name");
		elements.put(element.getName(), element);
		element.setParentFolder(this);
	}
}
