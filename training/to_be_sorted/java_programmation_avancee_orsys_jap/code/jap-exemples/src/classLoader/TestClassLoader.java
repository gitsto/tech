package classLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestClassLoader {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		URL url1 = null;
		URL url2 = null;
		try {
			url1 = new URL("file:///C:/Wspaces/JAVA/jap-tps/libs-classloader/compteur1.zip");
			url2 = new URL("file:///C:/Wspaces/JAVA/jap-tps/libs-classloader/compteur2.zip");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		URL[] urls1 = new URL[] { url1 };
		URL[] urls2 = new URL[] { url2 };
		
		ClassLoader cl1 = new URLClassLoader(urls1);
		ClassLoader cl2 = new URLClassLoader(urls2);
		
		try {
			Class clz1 = cl1.loadClass("java.lang.String");
			Class clz2 = cl2.loadClass("java.lang.String");
			System.out.println("clz1 : " + clz1 + " :: " + clz1.getClassLoader());
			System.out.println("clz2 : " + clz2 + " :: " + clz2.getClassLoader());
			@SuppressWarnings("unused") String str1 = (String) clz1.newInstance();
			@SuppressWarnings("unused") String str2 = (String) clz2.newInstance();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Class clz1 = cl1.loadClass("tp02.ICompteur");
			Class clz2 = cl2.loadClass("tp02.ICompteur");
			Class clz3 = cl2.loadClass("tp02.CompteurSimple");
			System.out.println("clz1 : " + clz1 + " :: " + clz1.getClassLoader());
			System.out.println("clz2 : " + clz2 + " :: " + clz2.getClassLoader());
			System.out.println("clz3 : " + clz3 + " :: " + clz3.getClassLoader());
			System.out.println("clz3 assignable to clz1 : " +  clz1.isAssignableFrom(clz3));
			System.out.println("clz3 assignable to clz2 : " + clz2.isAssignableFrom(clz3));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
