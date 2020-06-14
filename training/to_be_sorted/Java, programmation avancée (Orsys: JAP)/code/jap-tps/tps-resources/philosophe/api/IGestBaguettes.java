package philosophe.api;

public interface IGestBaguettes {
	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException ;
	public void libBaguettes(int b1, int b2) throws IllegalArgumentException ;
}
