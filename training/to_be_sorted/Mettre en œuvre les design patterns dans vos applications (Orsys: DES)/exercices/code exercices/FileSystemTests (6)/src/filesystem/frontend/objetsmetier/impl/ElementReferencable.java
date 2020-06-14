/**
 * 
 */
package filesystem.frontend.objetsmetier.impl;

import filesystem.frontend.objetsmetier.intf.IElementReferencable;

public abstract class ElementReferencable extends Element implements
		IElementReferencable {

	public ElementReferencable(String nom) {
		super(nom);
	}
}
