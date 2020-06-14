package philosophe.securite.sign;

import java.io.*;

public class SignResponse  implements Serializable {
	static final long serialVersionUID = -8296046979693023265L;

	Object reponse;		//la r�ponse correspond soit � un Boolean soit � une IllegalArgumentException

	public SignResponse(Object rp) { reponse=rp; }
	public Object getReponse() { return reponse; }
	
	public byte[] toByteArray() {
		byte[] res = { 1, 0 };
		if (reponse instanceof Boolean) {
			res[0] = 0;
			res[1] = (byte) (((Boolean) reponse).booleanValue() ? 1 : 0);
		}
		return res;
	}
}
