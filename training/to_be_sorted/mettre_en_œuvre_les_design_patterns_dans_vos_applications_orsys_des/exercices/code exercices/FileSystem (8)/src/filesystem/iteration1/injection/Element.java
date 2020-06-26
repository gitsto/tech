package filesystem.iteration1.injection;

import java.util.Date;

public abstract class Element implements IElement {
	private IFolder dossierParent;
	private String nom;
	private Date creationDate = new Date();

	public Element(String nom) {
		this.nom = nom;
	}

	public void setParentFolder(IFolder dossierParent) {
		this.dossierParent = dossierParent;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	public String getName() {
		return nom;
	}

	public void setName(String nom) {
		this.nom = nom;
	}

	public IFolder getParentFolder() {
		return dossierParent;
	}
	

}
