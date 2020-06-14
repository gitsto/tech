package filesystem.iteration4;


public class File extends ReferencableElement implements IFile {

	private int size;

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

}
