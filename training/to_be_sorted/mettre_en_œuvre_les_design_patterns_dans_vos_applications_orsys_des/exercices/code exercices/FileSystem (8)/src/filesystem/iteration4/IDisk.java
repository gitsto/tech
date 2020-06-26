package filesystem.iteration4;

public interface IDisk {
	public abstract int getCapacity();

	public abstract ILogicalUnit createUnit(IPartition partition);

	public abstract ILogicalUnit getUnit(String unitName);

}