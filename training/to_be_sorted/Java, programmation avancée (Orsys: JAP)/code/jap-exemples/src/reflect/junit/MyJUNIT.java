package reflect.junit;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyJUNIT {
	public static void fail(String msg) throws MyJUNITException { throw new MyJUNITException(msg); }

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if (args.length !=1) return;
		try {
			Class clz = Class.forName(args[0]);
			Method[] meths = clz.getDeclaredMethods();
			for (int i = 0; i < meths.length; i++) {
				if (!meths[i].getName().startsWith("test")) continue;
				if (meths[i].getReturnType() != void.class) continue;
				if (meths[i].getParameterTypes().length != 0) continue;
				Object obj = clz.newInstance();
				try { 
					meths[i].invoke(obj);
					System.out.println("methode " + meths[i].getName() + " OK ");
				} catch(InvocationTargetException e) {
					Throwable e1 = e.getCause();
					if (e1 instanceof MyJUNITException) 
						System.out.println("methode " + meths[i].getName() + " KO : " + e1.getMessage());	
					else e.printStackTrace();
				}
			}
		} catch (Exception e) { e.printStackTrace(); }	
	}
}
