package filesystem.iteration1.injection;

public interface ILink extends IElement {
	ReferencableElement getReferenced();
	void setReferenced(ReferencableElement ref);
}