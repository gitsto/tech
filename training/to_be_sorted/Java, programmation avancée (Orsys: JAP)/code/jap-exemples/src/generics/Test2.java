package generics;
interface A {
	public void m1();
	public void m2();
}
public class Test2<T extends A> {
	T refT;
	
	public Test2(T r) { refT = r; refT.m1(); }
	T getRefT() { return refT; }
}
