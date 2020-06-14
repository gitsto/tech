package fr.jurbert.formation.orsys.jap.tp6.rmi;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.jurbert.formation.orsys.jap.tp6.gui.BaseComponent;
import fr.jurbert.formation.orsys.jap.tp6.gui.IComponentSource;

class RMIServerGui {

	private static RMIServerGui THE_INSTANCE = new RMIServerGui();

	private JPanel contentPane;

	private JFrame frame;

	private RMIServerGui() {

	}

	public static RMIServerGui getInstance() {
		return THE_INSTANCE;
	}

	public Container getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel(new GridBagLayout());
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			JLabel ghostLabel = new JLabel();
			ghostLabel.setPreferredSize(new Dimension(100, 0));
			contentPane.add(ghostLabel, constraints);
			constraints.gridx = 0;
			constraints.gridy = 0;
			JLabel ghostLabel2 = new JLabel();
			ghostLabel2.setPreferredSize(new Dimension(100, 0));
			contentPane.add(ghostLabel2, constraints);

		}

		return contentPane;
	}

	public void addBaguette(IComponentSource baguetteSource, int position) {
		BaseComponent baguette = baguetteSource.getComponent();
		baguette.setPreferredSize(new Dimension(50, 25));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = position;
		getContentPane().add(baguette, constraints);
	}

	public void init() {
		String frameName = "RMI baguettes service";
		frame = new JFrame(frameName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setContentPane(getContentPane());

		frame.pack();
		frame.setVisible(true);
	}

}
