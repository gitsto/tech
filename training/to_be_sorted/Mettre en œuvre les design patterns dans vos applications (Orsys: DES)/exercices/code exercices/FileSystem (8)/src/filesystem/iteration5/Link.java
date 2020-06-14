package filesystem.iteration5;

public class Link extends Element implements ILink {
 
	private ReferencableElement referenced;
	
	public Link(String nom, ReferencableElement referenced) {
		super(nom);
		this.referenced = referenced;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public ReferencableElement getReferenced() {
		return referenced;
	}

	@Override
	public void setReferenced(ReferencableElement ref) {
		this.referenced = ref;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitLink(this);
	}


}
