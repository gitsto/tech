package stream;

import java.io.*;

public class StResponse  implements Serializable {
	static final long serialVersionUID = -8296046979693023265L;

	Object reponse;		//la réponse correspond soit à un Boolean soit à une IllegalArgumentException

	public StResponse(Object rp) { reponse=rp; }
	public Object getReponse() { return reponse; }
}
