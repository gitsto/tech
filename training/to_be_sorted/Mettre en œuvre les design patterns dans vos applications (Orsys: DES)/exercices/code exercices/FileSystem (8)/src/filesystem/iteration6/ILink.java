package filesystem.iteration6;

public interface ILink extends IElement {
	ReferencableElement getReferenced();
	void setReferenced(ReferencableElement ref);
}