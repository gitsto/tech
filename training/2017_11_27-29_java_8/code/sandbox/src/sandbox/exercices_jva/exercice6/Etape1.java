package sandbox.exercices_jva.exercice6;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Etape1 {

	public static void nashornString() throws ScriptException {
		final ScriptEngineManager factory = new ScriptEngineManager();
		final ScriptEngine engine = factory.getEngineByName("nashorn");
		engine.eval("print('resultat:', 15 + 10)");
	}
	
	public static void main(String[] args) {
		try {
			nashornString();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
