package filesystem.iteration1.encapsulation;

public class Link extends Element {
 
	private ReferencableElement referenced;
	
	public Link(String nom, ReferencableElement referenced) {
		super(nom);
		this.referenced = referenced;
	}

	@Override
	public int getSize() {
		return 0;
	}

	public ReferencableElement getReferenced() {
		return referenced;
	}

	public void setReferenced(ReferencableElement ref) {
		this.referenced = ref;
	}

}
