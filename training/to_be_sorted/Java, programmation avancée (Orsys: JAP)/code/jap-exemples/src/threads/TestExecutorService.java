package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;;

public class TestExecutorService {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		String string0 = "azertyuiopqsdfghjklmwxcvbn";
		Future[] futures = new Future[20]; 
		
		for (int i = 0; i < 20; i++) {
			futures[i] = exec.submit(new SubstringTask(string0, i));
		}
		for (int i = 0; i < 20; i++) {
			try {
				System.out.println("resultat " + i + " : " + futures[i].get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}

@SuppressWarnings("unchecked")
class SubstringTask implements Callable {
	private String string;
	private int nb;
	
	public SubstringTask(String string, int nb) {
		super();
		this.string = string;
		this.nb = nb;
	}

	public Object call() throws Exception {
		Thread.sleep((long) (5000 * Math.random()));
		String sString = string.substring(0,nb);
		System.out.println("Tache substring " + nb + " : " + sString);
		return sString;
	}
	
}
