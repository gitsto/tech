package capteurs.stream;

import java.io.*;

public class StResponse  implements Serializable {
	static final long serialVersionUID = -8296046979693023265L;

	Object reponse;		//la r�ponse correspond à un Double

	public StResponse(Object rp) { reponse=rp; }
	public Object getReponse() { return reponse; }
}
