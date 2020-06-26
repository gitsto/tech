package philosophe.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class PhiloGUI1 {
	protected Frame frm;
	protected TextField txtManger;
	protected TextField txtReflechir;
		    
	class TextFieldUpdate implements Runnable {
		private TextField tField;
		private Color color;
		private String text;
				
		TextFieldUpdate(TextField tf, Color co, String tx) {
			tField = tf;
			color = co;
			text = tx;
		}
				
		public void run() {
			tField.setBackground(color);
			tField.setText(text);
		}
	}

	public void initialiser(int numero) {
		frm = new Frame("Essai de socket");
		frm.setLayout(new FlowLayout());
		frm.setLocation(0, 60 * numero + 10);
		txtReflechir = new TextField("philo " + numero + " demarre ........");
		txtManger = new TextField("philo " + numero + " demarre ........");
		frm.add(txtReflechir);
		frm.add(txtManger);		
		frm.pack();
		frm.setVisible(true);
	}

	public void reflechir(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtReflechir, Color.yellow,"philo " + numero + " discute et reflechit"));
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}
		
	public void seReposer(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtReflechir, Color.pink,"philo " + numero + " se repose"));
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}
	
	public void commander(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtManger, Color.blue,"philo " + numero + " attend la commande"));
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}
	
	public void attendre(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtManger, Color.red,"philo " + numero + " attend les baguettes"));
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}

	public void manger(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtManger, Color.green,"philo " + numero + " mange ..."));
		} catch (InterruptedException e) { e.printStackTrace(); 
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}
	
	public void digerer(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtManger, Color.pink,"philo " + numero + " digere ..."));
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}

	public void terminer(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtManger, Color.white,"philo " + numero + " termine"));
			SwingUtilities.invokeAndWait(new TextFieldUpdate(txtReflechir, Color.white,"philo " + numero + " termine"));
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (InvocationTargetException e) { e.printStackTrace(); }
	}
}
