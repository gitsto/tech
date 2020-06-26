package filesystem.iteration1.injection;

import java.util.Date;

public interface IElement {
	String getName();
	void setName(String name);
	Date getCreationDate();
	int getSize();
	IFolder getParentFolder();
	void setParentFolder(IFolder parentFolder);
}
