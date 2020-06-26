package filesystem.frontend.objetsmetier.intf;

/**
 * @author daniel.rosenblatt
 */
public interface IElement {

	String getNom();

	void setNom(String nom);

	int getTaille();

	IDossier getParent();

	void setParent(IDossier parent);
	
	IUniteLogique getUniteLogique();

	void accept(Visitor visitor);
}
