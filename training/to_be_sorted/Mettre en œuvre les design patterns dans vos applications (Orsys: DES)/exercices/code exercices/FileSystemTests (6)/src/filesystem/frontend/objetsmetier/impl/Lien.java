/**
 * 
 */
package filesystem.frontend.objetsmetier.impl;

import filesystem.frontend.objetsmetier.intf.IElementReferencable;
import filesystem.frontend.objetsmetier.intf.ILien;
import filesystem.frontend.objetsmetier.intf.Visitor;
import filesystem.shared.objetsmetier.intf.ILienHandle;

/**
 * @author daniel.rosenblatt
 *
 */
public class Lien extends Element implements ILien {

	private IElementReferencable elementReferencable;
	private ILienHandle handle;

	/**
	 * 
	 */
	public Lien(String nom, ElementReferencable elementReferencable) {
		super(nom);
		this.elementReferencable = elementReferencable;
	}

	@Override
	public int getTaille() {
		return 0;
	}

	@Override
	public IElementReferencable getElementReferencable() {
		return elementReferencable;
	}

	@Override
	public void setElementReferencable(IElementReferencable elementReferencable) {
		this.elementReferencable = elementReferencable;
	}

	@Override
	public ILienHandle getHandle() {
		return handle;
	}

	@Override
	public void setHandle(ILienHandle handle) {
		this.handle = handle;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitLien(this);
	}

}