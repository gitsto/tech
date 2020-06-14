package philosophe.securite.sign;

import java.io.*;

public class SignRequest implements Serializable {
	   static final long serialVersionUID = -4321405821600571046L;

	   String req;
	   int bag1;
	   int bag2;

	   public SignRequest(String r,int b1, int b2) { req=r; bag1 = b1; bag2 = b2; }
	   public String getRequete() { return req; }
	   public int getBaguette1() { return bag1; }
	   public int getBaguette2() { return bag2; }
	   
	   public byte[] toByteArray() {
		   byte[] b1 = req.getBytes();
		   byte[] b2 = new byte[b1.length + 8];
		   int i;
		   for (i = 0; i < b1.length; i++) b2[i] = b1[i];
		   complete(b2, i);
		   return b2;
	   }
	   
	   private void complete(byte[] b, int j) {
		   b[j] = (byte) (bag1 >> 24);
		   b[j + 1] = (byte) (bag1 >> 16);
		   b[j + 2] = (byte) (bag1 >> 8);
		   b[j + 3] = (byte) bag1;
		   b[j + 4] = (byte) (bag2 >> 24);
		   b[j + 5] = (byte) (bag2 >> 16);
		   b[j + 6] = (byte) (bag2 >> 8);
		   b[j + 7] = (byte) bag2;
	   }
	}
