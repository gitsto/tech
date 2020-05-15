package fr.orsys.jva.exercice6;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;

public class Etape1 {
	private static ScriptEngineManager manager;
	public static void main(String[] args) throws Exception {
		manager			= new ScriptEngineManager();
		List<ScriptEngineFactory> factories	= manager.getEngineFactories();
		for (ScriptEngineFactory scriptEngineFactory : factories) {
			System.out.println(scriptEngineFactory.getNames());
			System.out.println(scriptEngineFactory.getEngineName());
		}
		nashornString();
		nashornFile("simple.js");
//		timerTask();
		simple2();
	}
	
	private static void nashornFile(String filename) {
		ScriptEngine javaScript		= manager.getEngineByName("JavaScript");
		// dans un seul thread
		// InputStream inputStream		= Etape1.class.getResourceAsStream(filename);
		InputStream inputStream		= Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		InputStreamReader reader	= new InputStreamReader(inputStream);
		try {
			System.out.print("nashornFile ");
			javaScript.eval(reader);
			// accès a du JavaScript depuis Java
			System.out.println(javaScript.get("firstname"));
			int x	= Integer.parseInt(javaScript.get("x").toString());
			int y	= Integer.parseInt(javaScript.get("y").toString());
			System.out.println("nashornFile "+x*y);
			// appeler la fonction JavaScript product
			Invocable invocable	= (Invocable) javaScript;
			Object resultat		= invocable.invokeFunction("product", x, y);
			System.out.println("nashornFile "+resultat);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private static void nashornString() {
		ScriptEngine javaScript		= manager.getEngineByName("JavaScript");
		String jsStatement			= "print(10 * 20)";
		try {
			System.out.print("nashornString ");
			javaScript.eval(jsStatement);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static void timerTask() {
		final int DELAY	= 1000;
		TimerTask task	= new TimerTask() {
			@Override
			public void run() {
				System.out.println("timertask "+ Instant.now());
			}
		};
		try {
			Timer timer	= new Timer();
			timer.schedule(task, 0, DELAY);
			Thread.sleep(DELAY*10);
			task.cancel();
			timer.cancel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void simple2() throws Exception {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new FileReader("./simple2.js"));
		Invocable invocable = (Invocable) engine;
		
		System.out.println(invocable.invokeFunction("callJavaProduct", 2, 3));
	}
	
	public static int produit(int a, int b) {
		return a * b;
	}
}
