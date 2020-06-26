package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamCacheExemple {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bS;
		ObjectOutputStream oS;
		
		byte[] data;
		ObjectInputStream iS;
		
		A refA1 = new A();
		B refB1 = new B(refA1);
		B refB2 = new B(refA1);
	
		bS = new ByteArrayOutputStream();
		oS = new ObjectOutputStream(bS);
		
		oS.writeObject(refB1);
		oS.reset();
		refA1.setValue(1);
		refB1.setValue(1);
		oS.writeObject(refB1);
		
		
		oS.writeObject(refB1);
		refA1.setValue(10);
		oS.writeObject(refB2);
		refB1.setValue(1);
		oS.writeObject(refB1);
		
		data = bS.toByteArray();
		iS = new ObjectInputStream(new ByteArrayInputStream(data));
		
		B irefB1 = (B) iS.readObject();
		System.out.println("B1 " + irefB1 + " value de B1 " + irefB1.getValue() + " A1 " + irefB1.getA() + " value de A1 " + irefB1.getA().getValue());
		B irefB2 = (B) iS.readObject();
		System.out.println("B2 " + irefB2 + " value de B2 " + irefB2.getValue() + " A1 " + irefB2.getA() + " value de A1 " + irefB2.getA().getValue());
		B irefB3 = (B) iS.readObject();
		System.out.println("B1 " + irefB3 + " value de B1 " + irefB3.getValue() + " A1 " + irefB3.getA() + " value de A1 " + irefB3.getA().getValue());				

		oS.reset();

		refB1.setValue(2);	
		oS.writeObject(refB1);
		
		data = bS.toByteArray();
		iS = new ObjectInputStream(new ByteArrayInputStream(data));
		
		B irefB4 = (B) iS.readObject();		
		System.out.println("B1 " + irefB4 + " value de B1 " + irefB4.getValue() + " A1 " + irefB4.getA() + " value de A1 " + irefB4.getA().getValue());
	}
}

class A implements Serializable {
	private static final long serialVersionUID = 844947485772691651L;
	private int value;
	
	A() {}

	public int getValue() { return value; }
	public void setValue(int v) { value = v; }
}

class B implements Serializable {
	private static final long serialVersionUID = 6753164882585531672L;
	private A a;
	private int value;
	
	B(A _a) { a = _a; }

	public A getA() { return a; }
	public void setA(A _a) { a = _a; }
	
	public int getValue() { return value; }
	public void setValue(int v) { value = v; }
}