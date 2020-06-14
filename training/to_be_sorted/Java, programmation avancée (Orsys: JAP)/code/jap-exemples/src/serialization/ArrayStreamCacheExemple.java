package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArrayStreamCacheExemple {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bS;
		ObjectOutputStream oS;
		
		byte[] data;
		ObjectInputStream iS;
		
		int[] dataToBeTransferred = { 0, 1, 2, 3, 4 };
		
		bS = new ByteArrayOutputStream();
		oS = new ObjectOutputStream(bS);
		
		oS.writeObject(dataToBeTransferred);
		//oS.reset();
		dataToBeTransferred[0] = -1;
		oS.writeObject(dataToBeTransferred);
	
		data = bS.toByteArray();
		iS = new ObjectInputStream(new ByteArrayInputStream(data));
		
		int[] irefB1 = (int[]) iS.readObject();
		System.out.println("int[] " + irefB1[0]);
		int[] irefB2 = (int[]) iS.readObject();
		System.out.println("int[] " + irefB2[0]);
	}
}