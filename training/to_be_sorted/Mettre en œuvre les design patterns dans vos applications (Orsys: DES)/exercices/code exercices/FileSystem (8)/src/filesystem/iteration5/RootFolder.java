package filesystem.iteration5;

public class RootFolder extends Folder {

	ILogicalUnit unit;
	public RootFolder(ILogicalUnit unit) {
		super(unit.getName() + ":\\");
		this.unit = unit;
	}

	public ILogicalUnit getLogicalUnit() {
		return unit;
	}
}
