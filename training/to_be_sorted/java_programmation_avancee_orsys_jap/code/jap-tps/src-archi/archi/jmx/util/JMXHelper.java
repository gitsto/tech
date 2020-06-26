package archi.jmx.util;

public class JMXHelper {
	public synchronized static String argSign(Class<?>[] clz) {
		StringBuffer buf = new StringBuffer("(");
		for (int i = 0; i < clz.length; i++) {
			if (clz[i].equals(byte.class)) { buf.append("B"); continue; }
			if (clz[i].equals(short.class)) { buf.append("S"); continue; }
			if (clz[i].equals(int.class)) { buf.append("I"); continue; }
			if (clz[i].equals(long.class)) { buf.append("J"); continue; }
			if (clz[i].equals(float.class)) { buf.append("F"); continue; }
			if (clz[i].equals(double.class)) { buf.append("D"); continue;  }
			if (clz[i].equals(boolean.class)) { buf.append("Z"); continue; }
			buf.append("L" + clz[i].getName() + ";");
		}
		buf.append(")");
		return buf.toString();
	}
	
	public synchronized static String toArgSign(String s) {
		int p = s.indexOf(")");
		return s.substring(0, p + 1);
	}
}
