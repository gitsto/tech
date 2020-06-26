package filesystem.iteration4;

public class LogicalUnit implements ILogicalUnit {

	private String name;
	private IPartition partition;
	private IFormat format;

	public LogicalUnit(String name, IPartition partition) {
		this.name = name;
		this.partition = partition;
	}

	public String getName() {
		return name;
	}

	@Override
	public void format(IFormat format) {
		this.format = format;
		format.format(partition);
	}

	@Override
	public int getCapacity() {
		return partition.getCapacity();
	}

	public IFormat getFormat() {
		return format;
	}

}
