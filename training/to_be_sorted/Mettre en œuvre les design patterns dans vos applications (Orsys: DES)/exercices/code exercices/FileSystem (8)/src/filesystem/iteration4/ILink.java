package filesystem.iteration4;

public interface ILink extends IElement {
	ReferencableElement getReferenced();
	void setReferenced(ReferencableElement ref);
}