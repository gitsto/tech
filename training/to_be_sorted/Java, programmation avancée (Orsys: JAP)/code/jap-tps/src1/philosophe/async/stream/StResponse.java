package philosophe.async.stream;

import java.io.*;

public class StResponse  implements Serializable {
	static final long serialVersionUID = -8296046979693023265L;

	private Object reponse;		//la r�ponse correspond soit � un Boolean soit � une IllegalArgumentException

	public StResponse(Object rp) { reponse=rp; }
	public Object getReponse() { return reponse; }
}
