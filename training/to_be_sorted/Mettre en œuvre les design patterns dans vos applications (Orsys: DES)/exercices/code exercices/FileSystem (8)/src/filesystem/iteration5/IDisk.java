package filesystem.iteration5;

public interface IDisk {

	public abstract IPhysicalDisk getPhysicalDisk();

	public abstract int getCapacity();

	public abstract ILogicalUnit createUnit(int capacity);

	public abstract ILogicalUnit getUnit(String unitName);

}