package fr.jurbert.formation.orsys.jap.tp6.baguette;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import fr.jurbert.formation.orsys.jap.tp6.gui.BaseComponent;
import fr.jurbert.formation.orsys.jap.tp6.gui.IComponentSource;

public class Baguette implements IComponentSource {

	private boolean isUsed;

	private final GraphicalComponent component = new GraphicalComponent();

	public Baguette() {
	}

	public boolean isUsed() {
		return isUsed;
	}

	/**
	 * Use this method if the synchronization processus is outside.
	 * <p>
	 * Else you should use get() and leave() methods.
	 * @param isUsed
	 */
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
		component.repaint();
	}

	/**
	 * Use this method if the synchronization processus is handled
	 * by the baguette. This call is blocking until the baguette
	 * is available.
	 * <p>
	 * Else you should use setUsed() method.
	 */
	public synchronized void get() {
		while (isUsed) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		setUsed(true);
	}

	/**
	 * Use this method if the synchronization processus is handled
	 * by the baguette. This call is blocking until the baguette
	 * is available.
	 * <p>
	 * Else you should use setUsed() method.
	 */
	public synchronized void leave() {
		setUsed(false);

		notifyAll();
	}

	public BaseComponent getComponent() {
		return component;
	}

	private class GraphicalComponent extends BaseComponent {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform oldTransform = new AffineTransform(g2.getTransform());
			
			int width = getWidth();
			int height = getHeight();
			int poseW = width / 6;
			int poseH = height / 4;

			g2.translate(width / 2, height / 2);
			g2.rotate(getAlpha());
			g.setColor(Color.WHITE);
			g.fillRect(width * 1 / 6, -poseH / 2, poseW, poseH);
			g.setColor(Color.WHITE.darker());
			g.drawRect(width * 1 / 6, -poseH / 2, poseW, poseH);
			if (!isUsed) {
				// Baguette
				g.setColor(Color.BLACK);
				g.fillRect(-width / 2, 0, width, 1);
			}

			g2.setTransform(oldTransform);
		}
	}

}
