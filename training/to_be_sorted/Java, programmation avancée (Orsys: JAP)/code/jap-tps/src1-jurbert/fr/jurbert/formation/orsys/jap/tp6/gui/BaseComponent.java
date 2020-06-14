package fr.jurbert.formation.orsys.jap.tp6.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class BaseComponent extends JComponent {
	private static final long serialVersionUID = 1L;

	/**
	 * Angle (rotation of the component).
	 */
	private double alpha;

	/**
	 * The point on the center of the component.
	 */
	private Point center = new Point();

	/**
	 * Sets the location of the point located at the center of the component.
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 */
	public void setCenterLocation(int x, int y) {
		center.x = x;
		center.y = y;
		setLocation(x - getWidth() / 2, y - getHeight() / 2);
	}

	@Override
	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	@Override
	public void setSize(int width, int height) {
		setBounds(center.x - width / 2, center.y - height / 2, width, height);
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		super.setLocation(x, y);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color oldColor = g.getColor();
		Object oldAntialias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		super.paint(g);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAntialias);
		g.setColor(oldColor);
	}

}
