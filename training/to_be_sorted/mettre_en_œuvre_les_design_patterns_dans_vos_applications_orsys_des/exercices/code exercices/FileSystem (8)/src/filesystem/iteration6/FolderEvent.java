package filesystem.iteration6;

import java.util.EventObject;

public class FolderEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public FolderEvent(IFolder sourceFolder) {
		super(sourceFolder);
	}

}
