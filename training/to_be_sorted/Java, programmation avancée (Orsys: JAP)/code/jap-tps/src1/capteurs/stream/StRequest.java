package capteurs.stream;

import java.io.*;

public class StRequest implements Serializable {
	static final long serialVersionUID = -4321405821600571046L;
	
	private String req;
	
	public StRequest(String r) { req=r; }
	public String getReq() {return req; }

}
