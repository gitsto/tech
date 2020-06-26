package philosophe.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;


public class GestBaguettesGUI {
	private int nbBaguettes;
	private Label[] bagLabel;	
	private TextField[] bagState;
	private Button[] bagReset;
	private Button globalReset = new Button("reset global");
	private IGestBagGUI0 control;
		
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

	class GlobalResetImpl implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			for (int i = 0; i < nbBaguettes; i++) {
				control.libBaguettes(i);
				bagState[i].setBackground(Color.yellow);
				bagState[i].setText("baguette liberee");
			}
		}
	}

	class BagResetImpl implements ActionListener {
		int numero;
		BagResetImpl(int num) {
			numero = num;
		}

		public void actionPerformed(ActionEvent evt) {
			control.libBaguettes(numero);
			bagState[numero].setBackground(Color.yellow);
			bagState[numero].setText("baguette liberee");
		}
	}

	public GestBaguettesGUI(int nbB, String ttl, IGestBagGUI0 ctl) {
		nbBaguettes = nbB;
		bagLabel = new Label[nbBaguettes];
		bagState = new TextField[nbBaguettes];
		bagReset = new Button[nbBaguettes];
		control = ctl;

		Frame fr = new Frame(ttl);
		fr.setLayout(new BorderLayout());
		Panel leftPnl = new Panel();
		leftPnl.setLayout(new GridLayout(nbBaguettes, 1));
		for (int i = 0; i < nbBaguettes; i++) {
			bagLabel[i] = new Label("bag. " + i);
			leftPnl.add(bagLabel[i]);
		}

		Panel centerPnl = new Panel();
		centerPnl.setLayout(new GridLayout(nbBaguettes, 1));
		for (int i = 0; i < nbBaguettes; i++) {
			bagState[i] = new TextField("baguette non reclamee");
			centerPnl.add(bagState[i]);
		}

		Panel rightPnl = new Panel();
		rightPnl.setLayout(new GridLayout(nbBaguettes, 1));
		for (int i = 0; i < nbBaguettes; i++) {
			bagReset[i] = new Button("reset ");
			bagReset[i].addActionListener(new BagResetImpl(i));
			rightPnl.add(bagReset[i]);
		}

		globalReset.addActionListener(new GlobalResetImpl());
		fr.add(rightPnl, BorderLayout.EAST);
		fr.add(leftPnl, BorderLayout.WEST);
		fr.add(globalReset, BorderLayout.SOUTH);
		fr.add(centerPnl, BorderLayout.CENTER);
		fr.pack();
		fr.setVisible(true);
	}

	public void acqBaguettes(int numero, boolean rep) {
		try {
			if (rep) {
				SwingUtilities.invokeAndWait(new TextFieldUpdate(bagState[numero], Color.green,"baguette allouee"));
			} else {
				SwingUtilities.invokeAndWait(new TextFieldUpdate(bagState[numero], Color.red,"baguette refusee"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void libBaguettes(int numero) {
		try {
			SwingUtilities.invokeAndWait(new TextFieldUpdate(bagState[numero], Color.yellow,"baguette liberee"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
