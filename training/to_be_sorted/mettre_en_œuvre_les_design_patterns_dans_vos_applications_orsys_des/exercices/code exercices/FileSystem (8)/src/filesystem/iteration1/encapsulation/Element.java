package filesystem.iteration1.encapsulation;

import java.util.Date;

public abstract class Element {
	private Folder dossierParent;
	private String nom;
	private Date creationDate = new Date();

	public Element(String nom) {
		this.nom = nom;
	}

	public void setParentFolder(Folder dossierParent) {
		this.dossierParent = dossierParent;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getName() {
		return nom;
	}

	public void setName(String nom) {
		this.nom = nom;
	}

	public Folder getParentFolder() {
		return dossierParent;
	}
	
	public abstract int getSize();

}
