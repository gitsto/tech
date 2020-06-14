package classLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class TestClassLoader1 {

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
		
		ClassLoader cl1 = new URLClassLoader1(urls1);
		ClassLoader cl2 = new URLClassLoader1(urls2, cl1);
		ClassLoader cl3 = new URLClassLoader1(urls2);
		
		try {
			Class clz1 = cl1.loadClass("java.lang.String");
			Class clz2 = cl2.loadClass("java.lang.String");
			System.out.println("clz1 : " + clz1 + " :: " + clz1.getClassLoader());
			System.out.println("clz2 : " + clz2 + " :: " + clz2.getClassLoader());
			@SuppressWarnings("unused") String str1 = (String) clz1.newInstance();
			@SuppressWarnings("unused") String str2 = (String) clz2.newInstance();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			Class clz1 = cl1.loadClass("tp02.ICompteur");
			Class clz2 = cl2.loadClass("tp02.ICompteur");
			Class clz2b = cl2.loadClass("tp02.CompteurSimple");
			Class clz3 = cl3.loadClass("tp02.ICompteur");
			Class clz3b = cl3.loadClass("tp02.CompteurSimple");
			
			System.out.println("ICompteur via clz1 : " + clz1 + " :: " + clz1.getClassLoader());
			System.out.println("ICompteur via clz2 : " + clz2 + " :: " + clz2.getClassLoader());
			System.out.println("CompteurSimple via clz2 : " + clz2b + " :: " + clz2b.getClassLoader());
			System.out.println("ICompteur via clz3 : " + clz3 + " :: " + clz3.getClassLoader());
			System.out.println("CompteurSimple via clz3 : " + clz3b + " :: " + clz3b.getClassLoader());
			System.out.println("CompteurSimple via clz2 assignable to ICompteur via clz1 : " +  clz1.isAssignableFrom(clz2b));
			System.out.println("CompteurSimple via clz2 assignable to ICompteur via  clz2 : " + clz2.isAssignableFrom(clz2b));
			System.out.println("CompteurSimple via clz3 assignable to ICompteur via clz1 : " +  clz1.isAssignableFrom(clz3b));
			System.out.println("CompteurSimple via clz3 assignable to ICompteur via clz2 : " +  clz2.isAssignableFrom(clz3b));
			System.out.println("CompteurSimple via clz3 assignable to ICompteur via  clz3 : " + clz3.isAssignableFrom(clz3b));			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
