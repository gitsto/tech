package archi.common;

public class JAPGenericResponse extends JAPGenericRequest {

	private static final long serialVersionUID = -604808156268561338L;
	
	private static Class<?>[] NOCLASS = new Class<?>[0]; 
	private static Object[] NOOBJECT = new Object[0]; 
	
	private Object result;
	private boolean isException;

	public JAPGenericResponse(Object res, long id, boolean isE) {
		super(null, NOCLASS, NOOBJECT, null, id);
		result = res;
		isException = isE;
	}
	
	public JAPGenericResponse(Object res, long id) { this(res, id, false); }
	
	public JAPGenericResponse(JAPGenericRequest req, Object res, boolean isE) {
		super(req.getMethodName(),req.getParameterTypes(), req.getValues(), req.getResultType(), req.getIdent());
		result = res;
		isException = isE;
	}
	
	public JAPGenericResponse(JAPGenericRequest req, Object res) { this(req, res, false); }
	
	public Object getResult() { return result; }
	public boolean isException() { return isException; }
}
