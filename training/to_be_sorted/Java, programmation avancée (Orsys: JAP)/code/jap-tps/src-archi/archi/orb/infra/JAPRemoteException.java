package archi.orb.infra;

public class JAPRemoteException extends RuntimeException {
	private static final long serialVersionUID = 506453249625055305L;
	private Throwable wrapped;
	
	public JAPRemoteException(Throwable ex) { wrapped = ex; }
	public JAPRemoteException(String msg) { super(msg); }
	
	public String getMessage() { 
		if (wrapped != null) return wrapped.getMessage(); 
		else return super.getMessage();
	}
	
	public String toString() { 
		if (wrapped != null) return wrapped.toString(); 
		else return super.toString();
	}
}
