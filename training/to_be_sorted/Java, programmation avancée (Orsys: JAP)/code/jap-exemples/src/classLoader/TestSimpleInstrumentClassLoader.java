package classLoader;

import java.lang.reflect.Method;

public class TestSimpleInstrumentClassLoader {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {
			ClassLoader clder = new SimpleInstrumentClassLoader("C:\\Documents and Settings\\HORN\\Workspaces\\EclipseWS-ORSYS\\JAVA\\jap-exemples\\libs-classloader");
			Class clz = clder.loadClass("classLoader.annotated.AnnotatedHello");
			Method meth = clz.getMethod("main", new Class[] {String[].class });
			meth.invoke(null, new Object[] { args });
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
