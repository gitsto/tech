package filesystem.iteration6;

public interface IPhysicalDisk {

	void createPartition(String unitName, int capacity);

	IFormat format(ILogicalUnit unit, String format);

	int getCapacity();

}
