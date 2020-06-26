package filesystem.frontend.objetsmetier.intf;

import filesystem.shared.objetsmetier.intf.IFichierHandle;

public interface IFichier extends IElementReferencable {
	void setTaille(int taille);
	void setHandle(IFichierHandle handle);
	IFichierHandle getHandle();
}
