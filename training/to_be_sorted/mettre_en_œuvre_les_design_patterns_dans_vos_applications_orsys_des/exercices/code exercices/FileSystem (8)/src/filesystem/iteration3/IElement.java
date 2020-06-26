package filesystem.iteration3;

import java.util.Date;
import java.util.List;

public interface IElement {
	String getName();
	void setName(String name);
	Date getCreationDate();
	int getSize();
	IFolder getParentFolder();
	void setParentFolder(IFolder parentFolder);
	void accumulate(List<IElement> list);
	void accept(Visitor visitor);
}
