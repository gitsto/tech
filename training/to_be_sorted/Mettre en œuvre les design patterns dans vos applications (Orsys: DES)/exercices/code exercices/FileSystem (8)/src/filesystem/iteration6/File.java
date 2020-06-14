package filesystem.iteration6;


public class File extends ReferencableElement implements IFile {

	private int size;
	IPhysicalFile physicalFile;

	public File(String name, int size) {
		super(name);
		this.size = size;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFile(this);
	}

	@Override
	public void setPhysicalFile(IPhysicalFile physicalFile) {
		this.physicalFile = physicalFile;
	}

	@Override
	public void setSize(int newSize) {
		physicalFile.setSize(newSize);
		this.size = newSize;
	}

}
