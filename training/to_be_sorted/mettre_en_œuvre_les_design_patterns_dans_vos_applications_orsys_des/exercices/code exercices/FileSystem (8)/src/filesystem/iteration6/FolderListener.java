package filesystem.iteration6;

import java.util.EventListener;

public interface FolderListener extends EventListener {
	void fileAdded(FolderEvent evt);
	void fileRemoved(FolderEvent evt);
}
