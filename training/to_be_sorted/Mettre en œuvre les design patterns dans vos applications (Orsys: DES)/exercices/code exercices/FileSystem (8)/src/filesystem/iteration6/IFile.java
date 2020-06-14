package filesystem.iteration6;

public interface IFile extends IReferenceableElement {

	void setPhysicalFile(IPhysicalFile physicalFile);
	void setSize(int newSize);	
}
