package filesystem.iteration2;

import java.util.Date;
import java.util.List;

public abstract class Element implements IElement {
	private IFolder parentFolder;
	private String name;
	private Date creationDate = new Date();

	public Element(String name) {
		this.name = name;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public IFolder getParentFolder() {
		return parentFolder;
	}
	
	public void setParentFolder(IFolder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public void accumulate(List<IElement> list) {
		list.add(this);
	}

}
