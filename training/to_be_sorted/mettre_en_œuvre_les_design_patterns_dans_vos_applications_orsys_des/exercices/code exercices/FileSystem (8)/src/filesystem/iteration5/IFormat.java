package filesystem.iteration5;

public interface IFormat {

	void format(LogicalUnit logicalUnit);
	IPhysicalFile createFile(IFile file);

}
