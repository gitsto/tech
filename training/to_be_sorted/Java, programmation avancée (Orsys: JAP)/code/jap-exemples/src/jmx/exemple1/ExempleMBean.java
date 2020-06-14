package jmx.exemple1;

public interface ExempleMBean {
	public void operation1(String nm);
	public void operation1();
	public String operation2();
	public String getReadOnly();
	public void setReadWrite(String nm);
	public String getReadWrite();
}
