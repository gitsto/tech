package fr.jurbert.formation.orsys.jap.tp6.philo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import fr.jurbert.formation.orsys.jap.tp6.gui.BaseComponent;
import fr.jurbert.formation.orsys.jap.tp6.gui.IComponentSource;
import fr.jurbert.formation.orsys.jap.tp6.resto.ITable;
import fr.jurbert.formation.orsys.jap.tp6.resto.Plate;
import fr.jurbert.formation.orsys.jap.tp6.resto.Restaurant;

public class Philosophe implements IPhilosophe, IComponentSource {

	private final String name;

	private final int position;

	private Plate plate;

	private final Thread eatThread;

	private final Thread discussThread;

	private final GraphicalComponent component = new GraphicalComponent();

	private boolean isLeaving;
	
	private boolean hasLeft;

	/**
	 * Philosophe state. If true, philosophe is eating.
	 */
	private boolean isEating;

	/**
	 * Philosophe state. If true, philosophe is discussing.
	 */
	private boolean isDiscussing;

	/**
	 * The restaurant in which philosophes are eating and speaking.
	 */
	private Restaurant restaurant;

	/**
	 * The table that will own the baguettes.
	 */
	private ITable table;
	
	/**
	 * Indicates if philosophe needs to eat (if true).
	 */
	private boolean isHungry = (Math.random() > 0.5);
	
	/**
	 * Indicates if philosophe don't want to speak (if true).
	 */
	private boolean isAutist = false;

	public Philosophe(String name, int position) {
		this.name = name;
		this.position = position;

		TachePhilosophe eatTask = new EatTask(this);
		this.eatThread = new Thread(new RepeatTachePhilosophe(eatTask));
		TachePhilosophe discussTask = new DiscussTask(this);
		this.discussThread = new Thread(new RepeatTachePhilosophe(discussTask));
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#getPosition()
	 */
	public int getPosition() {
		return position;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#getPhilosopheName()
	 */
	public String getPhilosopheName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#getPlate()
	 */
	public Plate getPlate() {
		return plate;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#enterInRestaurant(fr.jurbert.formation.orsys.jap.tp1.q4.Restaurant)
	 */
	public void enterInRestaurant(Restaurant restaurant) {
		if (restaurant == null) {
			throw new IllegalArgumentException("\'restaurant\' parameter cannot be null");
		}
		this.restaurant = restaurant;
		this.table = restaurant.getTable();
		this.plate = table.getPlate(position);

		eatThread.start();
		discussThread.start();
	}

	public void leaveRestaurant() {
		if (!plate.isEmpty()) {
			// Makes philosophe enough hungry to empty his plate!
			isHungry = true;
		}
		isLeaving = true;
	}
	
	public void waitUntilLeft() {
		try {
			eatThread.join();
			discussThread.join();
			
			hasLeft = true;
			component.repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#getRestaurant()
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Warning : blocking until baguettes are gotten. 
	 */
	public void acquireBaguettes() {
		System.out.println(name + " is waiting for baguettes");
		table.getBaguettes(position);
		System.out.println(name + " has acquired baguettes");
	}

	public void releaseBaguettes() {
		System.out.println(name + " releases baguettes");
		table.rendBaguettes(position);
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#isDiscussing()
	 */
	public boolean isDiscussing() {
		return isDiscussing;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#setDiscussing(boolean)
	 */
	public void setDiscussing(boolean isDiscussing) {
		this.isDiscussing = isDiscussing;

		component.repaint();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#isEating()
	 */
	public boolean isEating() {
		return isEating;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.IPhilosophe#setEating(boolean)
	 */
	public void setEating(boolean isEating) {
		this.isEating = isEating;

		component.repaint();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe#isLeaving()
	 */
	public boolean isLeaving() {
		return isLeaving;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe#isHungry()
	 */
	public boolean isHungry() {
		return isHungry;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe#setHungry(boolean)
	 */
	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe#isAutist()
	 */
	public boolean isAutist() {
		return isAutist;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe#setAutist(boolean)
	 */
	public void setAutist(boolean isAutist) {
		this.isAutist = isAutist;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.IComponentSource#getComponent()
	 */
	public BaseComponent getComponent() {
		return component;
	}

	private class GraphicalComponent extends BaseComponent {
		private static final long serialVersionUID = 1L;

		public GraphicalComponent() {
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (!hasLeft) {
				Graphics2D g2 = (Graphics2D) g;
				AffineTransform oldTransform = new AffineTransform(g2.getTransform());
				
				int width = getWidth();
				int height = getHeight();
				int d = width / 4;
				int r = d / 2;
				g2.translate(width / 2, height / 2);
				g2.rotate(getAlpha());
				
				final Color skinColor;
				if (isHungry || isEating) {
					skinColor = new Color(255, 200, 200);
				} else {
					skinColor = new Color(200, 200, 230);
				}
				// Tête
				g.setColor(skinColor);
				g.fillOval(-r, -r, d, d);
				// Cheveux
				final Color hairsColor;
				if (isAutist) {
					hairsColor = Color.GREEN.darker();
				} else {
					hairsColor = new Color(120, 100, 40);
				}
				g.setColor(hairsColor);
				g.fillOval(-r, -r, d * 2 / 3, d);
				// Nez
				int wNez = d / 4;
				int hNez = d / 6;
				g.setColor(skinColor);
				g.fillOval(r - wNez / 2, -hNez / 2, wNez, hNez);
				g.setColor(new Color(80, 80, 0));
				g.drawOval(r - wNez / 2, -hNez / 2, wNez, hNez);
				
				// Discuss
				if (isDiscussing) {
					// Link
					Polygon link = new Polygon();
					link.addPoint(r, -r / 2);
					link.addPoint(r * 3 / 2, -r);
					link.addPoint(d, -r);
					g.setColor(Color.WHITE);
					g.fillPolygon(link);
					g.setColor(Color.BLACK);
					g.drawPolygon(link);
					// Bulle
					g.setColor(Color.WHITE);
					g.fillOval(r, -r * 3 / 2, d, r);
					g.setColor(Color.BLACK);
					g.drawOval(r, -r * 3 / 2, d, r);
				}
				
				// Eat
				if (isEating) {
					// Hand
					g.setColor(skinColor);
					g.fillOval(-r, r, d * 3 / 2, r);
					// Baguettes
					g.setColor(Color.BLACK);
					g.drawLine(d, 0, d, d + r / 2);
					int dx = r / 4;
					g.drawLine(d + dx, 0, d - dx * 2 / 3, d + r / 2);
				}
				
				g2.setTransform(oldTransform);
			}
			}
	}

}
