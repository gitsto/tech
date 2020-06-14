package filesystem.iteration2;

import java.util.Iterator;

public class Add1ToFilesStartingWithF extends Visitor {

	@Override
	public void visitFile(IFile file) {
//		if ("f".equals(file.getName())) {
//			file.setSize(file.getSize() + 1);
//		}
	}

	@Override
	public void visitFolder(IFolder folder) {
		for (Iterator<IElement> it = folder.iterator(); it.hasNext(); ) {
			it.next().accept(this);
		}
	}

}
