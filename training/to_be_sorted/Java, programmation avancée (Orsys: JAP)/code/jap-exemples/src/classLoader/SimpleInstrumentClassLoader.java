package classLoader;

import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class SimpleInstrumentClassLoader extends ClassLoader {

	private ClassPool pool;
	
	public SimpleInstrumentClassLoader(ClassLoader parent, String cPath) throws Exception { 
		super(parent);
		pool = ClassPool.getDefault();
		pool.insertClassPath(cPath);
	}
	
	public SimpleInstrumentClassLoader( String cPath)  throws Exception {
		pool = ClassPool.getDefault();
		pool.insertClassPath(cPath);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		try {
			CtClass cc = pool.get(name);
			CtMethod[] meths = cc.getMethods();
			
			for (int i = 0; i < meths.length; i++) {
				System.out.println();
				Object[] annots = meths[i].getAnnotations();
				for (int j = 0; j < annots.length; j++) {
					Class clz = annots[j].getClass();
					Method mts = clz.getMethod("annotationType");
					Class tp = (Class) mts.invoke(annots[j]);		
					if (tp.equals(classLoader.annotations.Instrument.class)) {
						String sBefore = "long __debut = System.currentTimeMillis(); System.out.println(\"debut de la methode " + meths[i].getName() + "\");";
						meths[i].insertBefore(sBefore);
						String sAfter = "long __duree = System.currentTimeMillis(); System.out.println(\"duree de la methode " + meths[i].getName() + " = \" + __duree);"; 
						meths[i].insertAfter(sAfter);
					}
				}
			}
			
			byte[] b = cc.toBytecode();
			return defineClass(name, b, 0, b.length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClassNotFoundException();
		}
	}
}
