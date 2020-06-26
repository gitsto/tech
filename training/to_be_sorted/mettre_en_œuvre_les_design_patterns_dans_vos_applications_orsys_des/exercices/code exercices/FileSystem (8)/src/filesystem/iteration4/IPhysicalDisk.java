package filesystem.iteration4;

public interface IPhysicalDisk {
	void createUnit(String unitName, IPartition partition);
	int getCapacity();
	IFormat getFormat(String formatName);
}
