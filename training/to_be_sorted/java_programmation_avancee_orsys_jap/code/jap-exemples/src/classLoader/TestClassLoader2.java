package classLoader;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestClassLoader2 {
	@SuppressWarnings("unchecked")
	public static void main(String[ ] args) {	
		URL url = null;
		
		try {
			url = new URL("file:///C:/Wspaces/JAVA/jap-examples/libs-classloader/philosophe0.jar");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		URL[] urls = new URL[] { url };
		
		URLClassLoader cl1 = new URLClassLoader(urls);
		try {
			Class clazz= cl1.loadClass("philosophe0.LanceurPhiloActif");
			Method meth = clazz.getDeclaredMethod("main", new Class[ ] { String[].class }); 
			meth.invoke(null, new Object[] { args });
		}	catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
