package stream;

import java.io.*;

public class StRequest implements Serializable {
	   static final long serialVersionUID = -4321405821600571046L;

	   String req;
	   int bag1;
	   int bag2;

	   public StRequest(String r,int b1, int b2) { req=r; bag1 = b1; bag2 = b2; }
	   public String getRequete() { return req; }
	   public int getBaguette1() { return bag1; }
	   public int getBaguette2() { return bag2; }
	}
