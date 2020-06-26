package archi.orb.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ORBHelper {

	public static Class<?>[] getAnnotatedInterfaces(Class<?> cl, Class<? extends Annotation> an) {
		List<Class<?>> remoteInterfaces = new ArrayList<Class<?>>();
		Class<?>[] itfs = cl.getInterfaces();
		for (int i = 0; i < itfs.length; i++) {
			if(itfs[i].isAnnotationPresent(an))remoteInterfaces.add(itfs[i]);
		}
		return remoteInterfaces.toArray(new Class[0]);
	}
	
	public static Method[] getAnnotatedMethods(Class<?> cl, Class<? extends Annotation> an) {
		List<Method> remoteMethods = new ArrayList<Method>();
		Class<?>[] itfs = cl.getInterfaces();
		for (int i = 0; i < itfs.length; i++) {
			Method[] meths = itfs[i].getMethods();
			for (int j = 0; j < meths.length; j++) {
				if(itfs[i].isAnnotationPresent(an)) {
					if (!remoteMethods.contains(meths[j])) remoteMethods.add(meths[i]);
				}
			}	
		}
		return remoteMethods.toArray(new Method[0]);
	}
	
	public static Method[] getMethodsFromAnnotatedInterfaces(Class<?> cl, Class<? extends Annotation> an) {
		List<Method> remoteMethods = new ArrayList<Method>();
		Class<?>[] itfs = cl.getInterfaces();
		for (int i = 0; i < itfs.length; i++) {
			if(!itfs[i].isAnnotationPresent(an)) continue;
			Method[] meths = itfs[i].getMethods();
			for (int k = 0; k < meths.length; k++) { 
				if (!remoteMethods.contains(meths[k])) remoteMethods.add(meths[k]);
			}
		}
		return remoteMethods.toArray(new Method[0]);
	}
	
	public static String[] getAnnotatedInterfaceNames(Class<?> cl, Class<? extends Annotation> an) {
		Class<?>[] itfs = getAnnotatedInterfaces(cl, an);
		String[] res = new String[itfs.length];
		for (int i = 0; i < itfs.length; i++) res[i] = itfs[i].getName();
		return res;
	}
	
	public static Class<?>[] getClasses(String[] str) throws ClassNotFoundException {
		List<Class<?>> l = new ArrayList<Class<?>>();
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null) continue;
			l.add(Class.forName(str[i]));
		}
		return (Class<?>[]) l.toArray(new Class<?>[0]);
	}
	
	//private static String cast(CtClass cl) {
//	String nm = cl.getName();
//	if (!cl.isPrimitive()) return "(" + nm + ")";
//	switch(nm.charAt(0)) {
//		case 'd' : return "(Double)";
//		case 'f' : return "(Float)"; 
//		case 'l' : return "(Long)";
//		case 'i' : return "(Integer)";
//		case 's' : return "(Short)";
//		case 'b' : if (nm.charAt(1) == 'y') return "(Byte)";
//					else return "(Boolean)";
//		case 'c' : return "(Character)";
//	}
//	return "error";
//}

}
