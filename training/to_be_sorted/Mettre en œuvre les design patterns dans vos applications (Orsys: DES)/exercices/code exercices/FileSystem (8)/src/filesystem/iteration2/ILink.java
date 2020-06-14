package filesystem.iteration2;

public interface ILink extends IElement {
	ReferencableElement getReferenced();
	void setReferenced(ReferencableElement ref);
}