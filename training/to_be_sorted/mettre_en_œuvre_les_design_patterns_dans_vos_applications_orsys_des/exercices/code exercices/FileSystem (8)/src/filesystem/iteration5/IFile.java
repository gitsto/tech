package filesystem.iteration5;

public interface IFile extends IReferenceableElement {

	void setPhysicalFile(IPhysicalFile physicalFile);
	void setSize(int newSize);	
}
