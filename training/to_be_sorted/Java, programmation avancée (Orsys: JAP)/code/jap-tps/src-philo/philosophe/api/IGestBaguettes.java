package philosophe.api;
import archi.orb.infra.JAPRemote;

@JAPRemote
public interface IGestBaguettes {
	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException ;
	public void libBaguettes(int b1, int b2) throws IllegalArgumentException ;
}
