package fr.jurbert.formation.orsys.jap.tp6.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class Gui {

	private static final int TABLE_DIAMETER = 200;

	private static final int PHILOSOPHE_WIDTH = TABLE_DIAMETER * 2 / 3;

	private static final int TABLE_RAYON = TABLE_DIAMETER / 2;

	private static final int PLATE_DIAMETER = TABLE_DIAMETER / 5;

	private static final int BAGUETTE_DIAMETER = PLATE_DIAMETER;

	private static Gui THE_INSTANCE = new Gui();

	private int nbPhilosophes;

	private ILeaveRestaurantListener listener;

	private JLayeredPane contentPane;

	private static final Point TABLE_CENTER = new Point(TABLE_DIAMETER, TABLE_DIAMETER);

	private JFrame frame;

	private boolean closing;

	private Gui() {

	}

	public static Gui getInstance() {
		return THE_INSTANCE;
	}

	public Container getContentPane() {
		if (contentPane == null) {
			contentPane = new JLayeredPane();
			contentPane.setPreferredSize(new Dimension(2 * TABLE_DIAMETER, 2 * TABLE_DIAMETER));
		}

		return contentPane;
	}

	public Container getContentPane(IComponentSource[] philosopheSources, IComponentSource tableSource) {
		Container contentPane = getContentPane();

		// Add table
		BaseComponent table = tableSource.getComponent();
		table.setSize(TABLE_DIAMETER, TABLE_DIAMETER);
		table.setCenterLocation(TABLE_CENTER.x, TABLE_CENTER.y);
		contentPane.add(table, new Integer(0));

		// Add philosophes
		int nbPhilo = philosopheSources.length;
		for (int i = 0; i < nbPhilo; i++) {
			BaseComponent philoComp = philosopheSources[i].getComponent();
			philoComp.setSize(PHILOSOPHE_WIDTH, PHILOSOPHE_WIDTH);
			double philoDistance = TABLE_RAYON + PHILOSOPHE_WIDTH / 2;
			double alpha = (2 * Math.PI) * i / nbPhilo;
			int dx = (int) (Math.cos(alpha) * philoDistance);
			int dy = (int) (Math.sin(alpha) * philoDistance);
			philoComp.setCenterLocation(TABLE_CENTER.x + dx, TABLE_CENTER.y + dy);
			philoComp.setAlpha(alpha + Math.PI);

			contentPane.add(philoComp, new Integer(10));
		}

		return contentPane;
	}

	public void addPlate(IComponentSource plateSource, int position) {
		BaseComponent plate = plateSource.getComponent();
		plate.setSize(PLATE_DIAMETER, PLATE_DIAMETER);
		double alpha = (2 * Math.PI) * position / nbPhilosophes;
		double plateDistance = TABLE_RAYON - PLATE_DIAMETER * 3 / 4;
		int dx = (int) (Math.cos(alpha) * plateDistance);
		int dy = (int) (Math.sin(alpha) * plateDistance);
		plate.setCenterLocation(TABLE_CENTER.x + dx, TABLE_CENTER.y + dy);

		getContentPane().add(plate, new Integer(5));
	}

	public void addBaguette(IComponentSource baguetteSource, int position) {
		BaseComponent baguette = baguetteSource.getComponent();
		baguette.setSize(BAGUETTE_DIAMETER, BAGUETTE_DIAMETER);
		double alpha = (2 * Math.PI) * (-0.5 + position) / nbPhilosophes;
		double baguetteDistance = TABLE_RAYON - BAGUETTE_DIAMETER * 3 / 4;
		int dx = (int) (Math.cos(alpha) * baguetteDistance);
		int dy = (int) (Math.sin(alpha) * baguetteDistance);
		baguette.setCenterLocation(TABLE_CENTER.x + dx, TABLE_CENTER.y + dy);
		baguette.setAlpha(alpha + Math.PI);

		getContentPane().add(baguette, new Integer(5));
	}

	public void preinit(int nbPhilosophes, ILeaveRestaurantListener listener) {
		this.nbPhilosophes = nbPhilosophes;
		this.listener = listener;
	}

	public void init(IComponentSource[] philosopheSources, IComponentSource tableSource, String baguettesServiceInfo) {
		String frameName = "Philosophes" + " (" + baguettesServiceInfo + ")";
		frame = new JFrame(frameName);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new MyWindowListener());

		frame.setContentPane(getContentPane(philosopheSources, tableSource));

		frame.pack();
		frame.setVisible(true);
	}

	private void notifyClosing() {
		JLabel label = new JLabel("Philosophes are leaving restaurant. Please wait...");
		Dimension preferredSize = label.getPreferredSize();
		label.setSize(preferredSize);
		label.setLocation(TABLE_DIAMETER - preferredSize.width / 2, 2);
		getContentPane().add(label, new Integer(50));

		frame.setTitle(frame.getTitle() + " - leaving...");
	}

	class MyWindowListener implements WindowListener {

		public void windowActivated(WindowEvent e) {
		}

		public void windowClosed(WindowEvent e) {
		}

		public void windowClosing(WindowEvent e) {
			if (!closing) {
				closing = true;
				notifyClosing();
				if (listener != null) {
					listener.leave();
				}
			} else {
				System.exit(0);
			}
		}

		public void windowDeactivated(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowOpened(WindowEvent e) {
		}

	}

}
