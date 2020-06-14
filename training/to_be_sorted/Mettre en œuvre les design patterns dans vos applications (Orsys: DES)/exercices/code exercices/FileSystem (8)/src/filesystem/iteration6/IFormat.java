package filesystem.iteration6;

public interface IFormat {

	void format(LogicalUnit logicalUnit);
	IPhysicalFile createFile(IFile file);

}
