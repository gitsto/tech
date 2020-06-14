package filesystem.iteration3;

public interface IDisk {

	public abstract IPhysicalDisk getPhysicalDisk();

	public abstract int getCapacity();

	public abstract ILogicalUnit getUnit(String unitName);

	public ILogicalUnit createUnit(IPartition partition);

}