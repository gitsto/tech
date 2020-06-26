package classLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoader1 extends URLClassLoader {
	public URLClassLoader1(URL[] arg0, ClassLoader arg1) { super(arg0, arg1); }
	public URLClassLoader1(URL[] arg0) { super(arg0); }
}
