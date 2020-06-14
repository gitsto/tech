package filesystem.frontend.objetsmetier.intf;

import filesystem.shared.objetsmetier.intf.ILienHandle;

/**
 * @author  daniel.rosenblatt
 */
public interface ILien extends IElement {
	IElementReferencable getElementReferencable(); 
	void setElementReferencable(IElementReferencable elementReferencable);

	void setHandle(ILienHandle lienHandle);
	ILienHandle getHandle();
}
