package filesystem.iteration1.injection;


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

}
