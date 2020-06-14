package archi.orb.util;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

public class JAPStubGenerator {

	private static CtClass EXCEPTION_CLASS;
	private static CtClass INETADDRESS_CLASS;
	private static CtClass INT_CLASS;
	private static CtClass JAPGENERICSTUB_CLASS;
	
	private static String genStubClass = "archi.orb.infra.JAPGenericStub";
	private static String genReqClass = "archi.common.JAPGenericRequest";
	private static String genRespClass = "archi.common.JAPGenericResponse";
	
	CtClass ctClass;
	private static int numero;

	static { 
		try {
			EXCEPTION_CLASS = ClassPool.getDefault().get("java.lang.Exception");
			INETADDRESS_CLASS = ClassPool.getDefault().get("java.net.InetAddress");
			JAPGENERICSTUB_CLASS = ClassPool.getDefault().get(genStubClass);
			INT_CLASS = ClassPool.getDefault().get("int");
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String pref1 = "  ";
	private static String pref2 = "    ";
	private static String pref3 = "      ";
	private static String pref4 = "         ";
	
	private static String cast(CtClass cl, String expr) {
		String nm = cl.getName();
		if (!cl.isPrimitive()) return "(" + nm + ") " + expr;
		switch(nm.charAt(0)) {
			case 'd' : return "((Double) " + expr + ").doubleValue()";
			case 'f' : return "((Float) " + expr + ").floatValue()"; 
			case 'l' : return "((Long) " + expr + ").longValue()";
			case 'i' : return "((Integer) " + expr + ").intValue()";
			case 's' : return "((Short) " + expr + ").shortValue()";
			case 'b' : if (nm.charAt(1) == 'y') return "((Byte) " + expr + ").byteValue()";
						else return "((Boolean) " + expr + ").booleanValue()";
			case 'c' : return "((Character) " + expr + ").charValue()";
		}
		return "error";
	}
	
	public CtClass generateStubCtClass(String sName, String[] iNames) throws NotFoundException, CannotCompileException {
		ctClass = ClassPool.getDefault().makeClass(sName +"_Stub" + numero++);
		ctClass.addInterface(ClassPool.getDefault().get("java.io.Serializable"));
		ctClass.setSuperclass(JAPGENERICSTUB_CLASS);
		
		CtClass[] iClasses = new CtClass[iNames.length];
		for (int i =0; i < iNames.length; i++) {
			iClasses[i] = ClassPool.getDefault().get(iNames[i]);
		}
		
		for (int i = 0; i < iNames.length; i++) ctClass.addInterface(iClasses[i]);

		CtConstructor ctConstructor = new CtConstructor(new CtClass[] { INETADDRESS_CLASS, INT_CLASS }, ctClass);
		ctConstructor.setBody("{ super($1, $2); }");
		ctConstructor.setExceptionTypes(new CtClass[] { EXCEPTION_CLASS });
		ctClass.addConstructor(ctConstructor);

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < iClasses.length; i++) {
			CtMethod[] meths = iClasses[i].getDeclaredMethods();
			for (int j = 0; j < meths.length; j++) {
				CtMethod meth = new CtMethod(meths[j].getReturnType(), meths[j].getName(), meths[j].getParameterTypes(), ctClass);
				meth.setExceptionTypes(meths[j].getExceptionTypes());
				buf.setLength(0);
				createMethodBody(meth, buf);
				//System.out.println(meth.toString());  //TRACE
				//System.out.println(buf.toString());   //TRACE
				meth.setBody(buf.toString());
				ctClass.addMethod(meth);
			}
		}
		
		return ctClass;
	}
	
	public Class<?> generateStubClass(String sName, String[] iNames) throws NotFoundException, CannotCompileException {
		Class<?> clz = generateStubCtClass(sName, iNames).toClass();
		return clz;
	}

	private void createMethodBody(CtMethod meth, StringBuffer buf) throws NotFoundException {
		String methName = meth.getName();
		String rTypeName = meth.getReturnType().getName();
		CtClass[] args = meth.getParameterTypes();
		CtClass[] eTp = meth.getExceptionTypes();
		buf.append(pref1 + "{\n");
		buf.append(pref2 + "long rID = Thread.currentThread().getId();\n");
		if (args.length == 0) {
			buf.append(pref2 + genReqClass + " requete = new " + genReqClass + "(\"" + methName + "\",new Class[0], new Object[0]");
			buf.append("," + rTypeName + ".class, rID);\n");
		} else {
			buf.append(pref2 + genReqClass + " requete = new " + genReqClass + "(\"" + methName + "\",new Class[] { ");

			for (int i = 0; i < args.length; i++) {
				if (i > 0) buf.append(",");
				buf.append("" + args[i].getName()+".class");
			}
			buf.append(" }, $args, " + rTypeName + ".class, rID);\n");
		}
		buf.append(pref2 + "try {\n");
		buf.append(pref3 + "synchronized (outS) { outS.writeObject(requete); }\n");
		buf.append(pref3 + genRespClass + " res = (" + genRespClass + ") getResponse(rID);\n");
		buf.append(pref3 + "if (res.isException()) {\n");
		buf.append(pref4 + "Exception exc = (Exception) res.getResult();\n");
		if (eTp.length != 0) {
			for (int i = 0; i < eTp.length; i++) {
				buf.append(pref4 + "if (exc instanceof " + eTp[i].getName() + ") throw exc;\n");
			}
		}
		buf.append(pref4 + "if (exc instanceof RuntimeException) throw exc;\n");
		buf.append(pref4 + "throw new RuntimeException(exc);\n");
		buf.append(pref3 + "}\n");
		if (!"void".equals(rTypeName)) buf.append(pref3 + "return " + cast(meth.getReturnType(), "res.getResult()") + ";\n");
		buf.append(pref2 + "} catch(RuntimeException e) {\n");
		buf.append(pref3 + "throw e;\n");
		buf.append(pref2 + "} catch(Exception e) {\n");
		buf.append(pref3 + "throw new RuntimeException(e);\n");
		buf.append(pref2 + "}\n");
		buf.append(pref1 + "}\n");
	}
}