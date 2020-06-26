package archi.jmx.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

public class JAPInterceptorFactory {
	private static long numero;
	
	private static CtClass OBJECT_CLASS;
	private static CtClass JAPGENERICMBEAN_CLASS;
	private static CtClass JAPGENERICINTERCEPTOR_CLASS;
	private static CtClass VOID_CLASS;
	private static CtClass LONG_CLASS;
	private static CtClass BOOLEAN_CLASS;
	private static CtClass CLASSARRAY_CLASS;
	private static CtClass STRINGARRAY_CLASS;
	private static CtClass BOOLEANARRAY_CLASS;

	private static String genMBeanClass = "archi.jmx.infra.JAPGenericInterceptorCtlMBean";
	private static String genInterClass = "archi.jmx.infra.JAPGenericInterceptor";
	private static String genReqClass = "archi.common.JAPGenericRequest";
	private static String genRespClass = "archi.common.JAPGenericResponse";
	
	private static String pref1 = "  ";
	private static String pref2 = "    ";
	private static String pref3 = "      ";
	private static String pref4 = "        ";
	
	private static HashMap<String[], CtClass> itfsPool = new HashMap<String[], CtClass>();

	private CtClass interceptorCtClass;
	//private CtClass interceptorItfCtClass;
	private CtClass[] serviceItfClasses;

	static { 
		try {
			OBJECT_CLASS = ClassPool.getDefault().get("java.lang.Object");
			JAPGENERICMBEAN_CLASS = ClassPool.getDefault().get(genMBeanClass);
			JAPGENERICINTERCEPTOR_CLASS = ClassPool.getDefault().get(genInterClass);
			VOID_CLASS = ClassPool.getDefault().get("void");
			LONG_CLASS = ClassPool.getDefault().get("long");
			BOOLEAN_CLASS = ClassPool.getDefault().get("boolean");
			CLASSARRAY_CLASS = ClassPool.getDefault().get("java.lang.Class[]");
			STRINGARRAY_CLASS = ClassPool.getDefault().get("java.lang.String[]");
			BOOLEANARRAY_CLASS = ClassPool.getDefault().get("boolean[]");
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public JAPInterceptorFactory() {}
	
	public synchronized Class<?> generateInterceptorClass(String[] iNames) throws CannotCompileException, NotFoundException {
		return generateInterceptorCtClass(iNames).toClass();
	}
	
	public synchronized Object generateInterceptor(String[] iNames) throws Exception {
		return generateInterceptorCtClass(iNames).toClass().newInstance();
	}
	
	public synchronized Object generateInterceptor(String[] iNames, Object obj) throws ClassNotFoundException {
		for (int i = 0; i < iNames.length; i++)
			if (!Class.forName(iNames[i]).isAssignableFrom(obj.getClass())) throw new IllegalArgumentException();
		try {
			Constructor<?> ctor = generateInterceptorClass(iNames).getConstructor(Object.class);
			return ctor.newInstance(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	public synchronized CtClass generateInterceptorCtClass(String[] iNames) throws CannotCompileException, NotFoundException {				
		Arrays.sort(iNames);
		CtClass res = itfsPool.get(iNames);
		if (res != null) return res;
		
		StringBuffer buf = new StringBuffer();
		CtMethod meth = null;
		String baseName = "JAPInterceptor__" + numero++;
		interceptorCtClass = ClassPool.getDefault().makeClass(baseName);
		serviceItfClasses = new CtClass[iNames.length];
		
		for (int i= 0; i < iNames.length; i++) serviceItfClasses[i] = ClassPool.getDefault().get(iNames[i]);
		
		interceptorCtClass.addInterface(JAPGENERICMBEAN_CLASS);
		for (int i= 0; i < iNames.length; i++) {
			interceptorCtClass.addInterface(serviceItfClasses[i]);
		}

		interceptorCtClass.setSuperclass(JAPGENERICINTERCEPTOR_CLASS);
		
		interceptorCtClass.addField(new CtField(LONG_CLASS, "ID", interceptorCtClass));
		interceptorCtClass.addField(new CtField(BOOLEANARRAY_CLASS, "preNotification", interceptorCtClass));
		interceptorCtClass.addField(new CtField(BOOLEANARRAY_CLASS, "postNotification", interceptorCtClass));
		
		List<String> methList = new ArrayList<String>();
		int index = 0;
		for (int i = 0; i < serviceItfClasses.length; i++) {
			CtMethod[] meths = serviceItfClasses[i].getDeclaredMethods();
			for (int j = 0; j < meths.length; j++) {
				meth = new CtMethod(meths[j].getReturnType(), meths[j].getName(), meths[j].getParameterTypes(), interceptorCtClass);
				meth.setExceptionTypes(meths[j].getExceptionTypes());
				buf.setLength(0);
				methList.add(meths[j].getName() + JMXHelper.toArgSign(meths[j].getSignature()));
				createMethodBody2(meth, buf, index++);
				//System.out.println(meth.getName() + meth.getSignature());  //TRACE
				//System.out.println(buf.toString());   //TRACE
				meth.setBody(buf.toString());
				interceptorCtClass.addMethod(meth);
			}
		}

		CtField fld1 = new CtField(CLASSARRAY_CLASS, "itfClasses", interceptorCtClass);
		fld1.setModifiers(Modifier.STATIC + Modifier.FINAL);
		
		CtField fld2 = new CtField(STRINGARRAY_CLASS, "methodIndex", interceptorCtClass);
		fld2.setModifiers(Modifier.STATIC + Modifier.FINAL);;

		buf.setLength(0);
		buf.append("new String[] { ");		
		for (int i = 0; i < methList.size(); i++) {
			if (i > 0) buf.append(", ");
			buf.append("\"" + methList.get(i) + "\"");
		}
		buf.append(" }");
		//System.out.println("methodIndex :" + buf.toString());
		interceptorCtClass.addField(fld2, buf.toString());

		buf.setLength(0);
		buf.append("new Class[ ] { ");
		for (int i = 0; i < iNames.length; i++) {
			if (i > 0) buf.append(", ");
			buf.append(iNames[i] + ".class");
		}
		buf.append(" }");
		//System.out.println("itfClasses :" + buf.toString());
		interceptorCtClass.addField(fld1, buf.toString());
		
		CtConstructor ctConstructor = null;
		ctConstructor = new CtConstructor(new CtClass[] { OBJECT_CLASS, BOOLEAN_CLASS, BOOLEAN_CLASS }, interceptorCtClass);
		buf.setLength(0);
		buf.append("{\n");
		buf.append(pref1 +"super( $1, true);\n");
		buf.append(pref1 +"preNotification = new boolean[" + index + "];\n");
		buf.append(pref1 +"for (int i =0; i < preNotification.length; i++) preNotification[i] = $2;\n");
		buf.append(pref1 +"postNotification = new boolean[" + index + "];\n");
		buf.append(pref1 +"for (int i =0; i < postNotification.length; i++) postNotification[i] = $3;\n");
		buf.append("}\n");
		ctConstructor.setBody(buf.toString());
		interceptorCtClass.addConstructor(ctConstructor);
		
		ctConstructor = new CtConstructor(new CtClass[] { OBJECT_CLASS }, interceptorCtClass);
		ctConstructor.setBody("{ this($1, false, false); }");
		interceptorCtClass.addConstructor(ctConstructor);
		ctConstructor = new CtConstructor(new CtClass[0], interceptorCtClass);
		ctConstructor.setBody("{ this(null, false, false); }");
		interceptorCtClass.addConstructor(ctConstructor);
		
		//construction de setTarget ...
		meth = new CtMethod(VOID_CLASS, "setTarget", new CtClass[] { OBJECT_CLASS }, interceptorCtClass);
		buf.setLength(0);
		createSetTargetBody(buf);
		//System.out.println("setTarget \n" + buf.toString());
		meth.setBody(buf.toString());;
		interceptorCtClass.addMethod(meth);
		
		CtMethod[] methJI = JAPGENERICMBEAN_CLASS.getDeclaredMethods();
		for (int i = 0; i < methJI.length; i++) {
			meth = new CtMethod(methJI[i].getReturnType(), methJI[i].getName(), methJI[i].getParameterTypes(), interceptorCtClass);
			meth.setExceptionTypes(methJI[i].getExceptionTypes());
			buf.setLength(0);
			if (methJI[i].getName().equals("configureNotificationMask")) createMethodBody0(meth, buf);
			else createMethodBody1(meth, buf);
			meth.setBody(buf.toString());
			interceptorCtClass.addMethod(meth);
		}

		itfsPool.put(iNames, interceptorCtClass);
		//System.out.println(interceptorCtClass.toString());
		return interceptorCtClass;
	}
	
	private CtClass findServiceInterface(CtMethod m) {
		for (int i = 0; i < serviceItfClasses.length; i++) {
			CtMethod[] ms = serviceItfClasses[i].getMethods();
			for (int j = 0; j < ms.length; j++)
			if ( m.getName().equals(ms[j].getName()) && m.getSignature().equals(ms[j].getSignature()) ) return serviceItfClasses[i];
		}
		return null;
	}
		
	private void createMethodBody2(CtMethod meth, StringBuffer buf, int n) throws NotFoundException {
		String methName = meth.getName();
		CtClass rType = meth.getReturnType();
		CtClass[] args = meth.getParameterTypes();
		buf.append(pref1 + "{\n");
		buf.append(pref2 + "long rID = 0L;\n");
		buf.append(pref2 + "long sID = 0L;\n");
		buf.append(pref2 + genReqClass + " req = null;\n");
		buf.append(pref2 + genRespClass + " rep = null;\n");
		buf.append(pref2 + "javax.management.Notification notif = null;\n");
		if (!rType.equals(VOID_CLASS)) buf.append(pref2 + rType.getName() + " res;\n");

		buf.append("\n");
		buf.append(pref2 + "_sync();\n\n");
		
		buf.append(pref2 + "if (preNotification[" + n + "] || postNotification[" + n + "]) { \n");
		buf.append(pref3 + "rID = Thread.currentThread().getId();\n");
		buf.append(pref3 + "sID = ID++;\n");
		if (args.length == 0) {
			buf.append(pref3 + "req = new " + genReqClass + "(\"" + methName + "\",new Class[0], new Object[0]");
			buf.append("," + rType.getName() + ".class, rID);\n");
		} else {
			buf.append(pref3 + "req = new " + genReqClass + "(\"" + methName + "\",new Class[] { ");
			for (int i = 0; i < args.length; i++) {
				if (i > 0) buf.append(",");
				buf.append("" + args[i].getName()+".class");
			}
			buf.append(" }, $args, " + rType.getName() + ".class, rID);\n");
		}
		
		String aStr = "";
		for (int i = 0; i < args.length; i++) {
			if (i !=0) aStr +=", ";
			aStr += "$" + (i + 1);
		}
		buf.append(pref2 + "}\n");
		buf.append(pref2 + "if (preNotification[" + n +"]) {\n");
		buf.append(pref3 + "_preNotif(req, sID);\n");
		buf.append(pref2 +"}\n");
		
		buf.append(pref2 + "try {\n");		
		String cast = findServiceInterface(meth).getName();
		if (rType.equals(VOID_CLASS)) {
			buf.append(pref3 + "((" + cast + ") target)." + methName + "(" + aStr + ");\n");
		} else {
			buf.append(pref3 + "res = (("  + cast + ") target)." + methName + "(" +aStr + ");\n");
		}

		buf.append(pref3 + "if (postNotification[" + n +"]) {\n");
		if (rType.equals(VOID_CLASS))
			buf.append(pref4 + "rep = new " + genRespClass + "(req, null);\n");
		else
			if (rType.isPrimitive()) buf.append(pref4 + "rep = new " + genRespClass + "(req, ($w) res);\n");
			else buf.append(pref4 + "rep = new " + genRespClass + "(req, res);\n");
		buf.append(pref4 + "_postNotif(rep, sID);\n");
		buf.append(pref3 + "}\n");
		buf.append(pref2 + "} catch(Exception e) {\n");
		buf.append(pref3 + "if (postNotification[" + n +"]) {\n");
		buf.append(pref4 + "rep = new " + genRespClass +"(req, e, true);\n");
		buf.append(pref4 + "_postNotif(rep, sID);\n");		
		buf.append(pref3 + "}\n");
		buf.append(pref3 + "throw e;\n");
		buf.append(pref2 + "}\n");
		if (!rType.equals(VOID_CLASS)) buf.append(pref2 + "return res;\n");
		buf.append(pref1 + "}\n");
	}
	
	private void createMethodBody1(CtMethod meth, StringBuffer buf) throws NotFoundException {
		buf.append("{ ");
		if (meth.getReturnType() != VOID_CLASS) buf.append("return ");
		buf.append("super." + meth.getName() + "(");
		for (int i = 0; i < meth.getParameterTypes().length; i++) {
			if (i > 0) buf.append(", ");
			buf.append("$" + (i + 1));
		}
		buf.append("); }");
	}
	
	private void createMethodBody0(CtMethod meth, StringBuffer buf) throws NotFoundException {
		buf.append("{ ");
		int l = meth.getParameterTypes().length;
		switch(l) {
		case 2 : 
			buf.append("_configNMask(methodIndex, preNotification, postNotification, $1, $2);\n"); 
			break;
		case 3 : 
			buf.append("_configNMask(methodIndex, preNotification, postNotification, $1, $2, $3);\n"); 
			break;
		case 4 : 
			buf.append("_configNMask(methodIndex, preNotification, postNotification, $1, $2, $3, $4);\n"); 
			break;
		}
		buf.append("}");
	}
	
	private void createSetTargetBody(StringBuffer buf) {
		buf.append("{\n");
		buf.append(pref1 + "if ($1 == null) { target = null; isActive = false; }\n");
		buf.append(pref1 + "for (int i = 0; i < itfClasses.length; i++)\n");
		buf.append(pref1 + "if (!itfClasses[i].isAssignableFrom($1.getClass())) throw new IllegalArgumentException(\"arg different de target\");\n");
		buf.append(pref1 + "target = $1;\n");
		buf.append("}\n");
	}
}

