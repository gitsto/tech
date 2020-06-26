package generics;
import java.io.Serializable;

public class Test3<S, T extends Cloneable & Comparable<S> & Serializable> {
	S refS;
	T refT;
	
	public Test3(S r1, T r2) { refS = r1; refT = r2; }
	S getRefS() { return refS; }
	T getRefT() { return refT; }

}
