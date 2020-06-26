/*
 * Created on 12 nov. 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package threads;

/**
 * @author Horn
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Semaphore {
	private int compteur;
	
	public Semaphore(int init)  {compteur = init; }
	
	public synchronized void P(int n) throws InterruptedException {
		while (compteur <n) wait(); 
		compteur -= n; 
	}
	public synchronized void V(int n)  { 
		compteur+=n;
		if (compteur >0) notifyAll();
	}
}
