package fr.jurbert.formation.orsys.jap.tp6.resto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import fr.jurbert.formation.orsys.jap.tp6.gui.BaseComponent;
import fr.jurbert.formation.orsys.jap.tp6.gui.IComponentSource;

public class Plate implements IComponentSource {

	private int capacity;

	private int content;

	private final GraphicalComponent component = new GraphicalComponent();

	public Plate(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity should be greater than 0");
		}
		this.capacity = capacity;
		this.content = 0;
	}

	public boolean isEmpty() {
		return content == 0;
	}

	public void fill() {
		content = capacity;

		component.repaint();
	}

	public void eat() {
		if (content > 0) {
			content--;

			component.repaint();
		} else {
			throw new IllegalStateException("No content to eat");
		}
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp2.v2.IComponentSource#getComponent()
	 */
	public BaseComponent getComponent() {
		return component;
	}

	private class GraphicalComponent extends BaseComponent {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			// Plate
			int width = getWidth();
			int height = getHeight();
			g.setColor(new Color(240, 255, 240));
			g.fillOval(0, 0, width - 1, height - 1);
			g.setColor(new Color(150, 150, 150));
			g.drawOval(0, 0, width - 1, height - 1);

			// Content
			Point plateCenter = new Point(width / 2, height / 2);
			int contentWidth = width / 4;
			int contentHeight = width / 7;
			g.setColor(new Color(220, 200, 50));
			for (int i = 0; i < content; i++) {
				double alpha = (2 * Math.PI) * i / capacity;
				double contentDistance = width / 2 - contentWidth;
				int x = (int) ((Math.cos(alpha) * contentDistance) - contentWidth / 2);
				int y = (int) ((Math.sin(alpha) * contentDistance) - contentHeight / 2);
				g.fillRect(plateCenter.x + x, plateCenter.y + y, contentWidth - 1, contentHeight - 1);
			}
		}
	}

}
