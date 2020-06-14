package filesystem.iteration1.encapsulation;


public class File extends ReferencableElement {

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
