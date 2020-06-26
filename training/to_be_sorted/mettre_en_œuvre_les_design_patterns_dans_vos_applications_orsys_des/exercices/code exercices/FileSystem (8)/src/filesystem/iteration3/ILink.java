package filesystem.iteration3;

public interface ILink extends IElement {
	ReferencableElement getReferenced();
	void setReferenced(ReferencableElement ref);
}