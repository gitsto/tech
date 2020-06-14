package jmx.exemple0;

public class Exemple implements ExempleMBean {
	

	private String ReadOnly = "Archibald";
	private String ReadWrite;

	public void operation1(String a0) {System.out.println("hello, " + a0); }
	public void operation1() { operation1(ReadOnly); }
	public String operation2() { return ReadWrite + ReadOnly; }
	public String getReadOnly() { return ReadOnly;}
	public synchronized void setReadWrite(String rw) { ReadWrite = rw; }
	public String getReadWrite() { return ReadWrite; }
}