package filesystem.iteration5;

public class LogicalUnit implements ILogicalUnit {

	private String name;
	private IFormat format;
	private int capacity;

	public LogicalUnit(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	@Override
	public void format(IFormat format) {
		this.format = format;
		format.format(this);
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	public IFormat getFormat() {
		return format;
	}

	@Override
	public IPhysicalFile createFile(IFile file) {
		return format.createFile(file);
	}

}
