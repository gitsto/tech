package classLoader;

import java.lang.reflect.Method;

public class TestSimpleCryptedClassLoader {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			System.out.println("exige 2 args : repertoire_racine_in decalage");
			return;
		}
		
		String baseDirIn = args[0];
		byte decalage = (byte) Integer.parseInt(args[1]);
		ClassLoader clder = new SimpleCryptedClassLoader(baseDirIn, decalage);
		try {
			Class clz = clder.loadClass("classLoader.crypted.CryptedHello");
			Object obj = clz.newInstance();
			Method meth = clz.getMethod("sayHello", new Class[] {});
			meth.invoke(obj, new Object[] {});
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
