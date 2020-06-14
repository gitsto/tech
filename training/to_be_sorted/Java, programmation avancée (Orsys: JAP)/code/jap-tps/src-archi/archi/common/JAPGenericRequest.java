package archi.common;

import java.io.*;

public class JAPGenericRequest implements Serializable {

	private static final long serialVersionUID = 629428949807417286L;

	private String methodName;
	private Class<?>[] paramTypes;
	private Class<?> resultType;
	private Object[] values;
	private long ident;
	
	private Object source;
	private Object target;
	
	public JAPGenericRequest(String mNm, Class<?>[] pTp, Object[] val, Class<?> rTp, long id, Object src, Object tgt) {
		methodName = mNm;
		paramTypes = pTp;
		values = val;
		resultType = rTp;
		ident = id;
	
		source = src;
		target = tgt;
	}
	
	public JAPGenericRequest(String mNm, Class<?>[] pTp, Object[] val, Class<?> rTp, long id) {
		this(mNm, pTp, val, rTp, id, null, null);
	}

	public String getMethodName() { return methodName; }
	public Class<?>[] getParameterTypes() { return paramTypes; }
	public Class<?> getResultType() { return resultType; }
	public Object[] getValues() { return values; }
	public long getIdent() { return ident; }

	public Object getSource() { return source; }
	public Object getTarget() { return target; }
}
