package filesystem.iteration5;

public interface ILogicalUnit {
	void format(IFormat format);
	String getName();
	int getCapacity();
	IPhysicalFile createFile(IFile file);
}
