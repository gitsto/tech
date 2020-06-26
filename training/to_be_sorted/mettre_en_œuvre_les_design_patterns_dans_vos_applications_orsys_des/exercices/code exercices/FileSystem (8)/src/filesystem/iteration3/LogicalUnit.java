package filesystem.iteration3;

public class LogicalUnit implements ILogicalUnit {

	private String name;
	private IPartition partition;

	public LogicalUnit(String name, IPartition partition) {
		this.name = name;
		this.partition = partition;
	}

	public String getName() {
		return name;
	}

}
