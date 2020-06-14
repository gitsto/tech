package philosophe.async.stream;

import java.io.*;

import philosophe.api.IPhiloCallback;

public class StRequest implements Serializable {
	static final long serialVersionUID = -4321405821600571046L;

	private String req;
	private int bag1;
	private int bag2;
	private IPhiloCallback callback;

	public StRequest(String r,int b1, int b2, IPhiloCallback cb) {
		req=r; bag1 = b1;
		bag2 = b2;
		callback = cb;
	}
	
	public StRequest(String r,int b1, int b2) { this(r, b1, b2, null); }
	
	public String getRequete() { return req; }
	public int getBaguette1() { return bag1; }
	public int getBaguette2() { return bag2; }
	public IPhiloCallback getCallback() { return callback; }
}
