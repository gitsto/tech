package fr.jurbert.formation.orsys.jap.tp6.resto;

import java.awt.Color;
import java.awt.Graphics;

import fr.jurbert.formation.orsys.jap.Utils;
import fr.jurbert.formation.orsys.jap.tp6.baguette.IBaguettesService;
import fr.jurbert.formation.orsys.jap.tp6.gui.BaseComponent;
import fr.jurbert.formation.orsys.jap.tp6.gui.Gui;

public class Table implements ITable {
	
	private static final int PLATE_CAPACITY = 3;

	private final int nbBaguettes;

	private final IBaguettesService baguettesService;
	
	private GraphicalComponent component = new GraphicalComponent();

	public Table(int nbBaguettes, IBaguettesService baguettesSource) {
		if (nbBaguettes <= 0) {
			throw new IllegalArgumentException("\'nbBaguettes\' parameter must be greater than 0");
		}
		if (baguettesSource == null) {
			throw new IllegalArgumentException("\'baguettesSource\' parameter cannot be null");
		}
		
		this.nbBaguettes = nbBaguettes;
		this.baguettesService = baguettesSource;
	}
	
	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp2.v2.ITable#getPlate(int)
	 */
	public Plate getPlate(int position) {
		if (position >= nbBaguettes) {
			throw new IllegalArgumentException("\'position\' parameter must be less than nbBaguettes=" + nbBaguettes);
		}
		
		Plate plate = new Plate(PLATE_CAPACITY);
		
		Gui.getInstance().addPlate(plate, position);

		return plate;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp2.IBaguettesManager#getBaguettes(int)
	 */
	public void getBaguettes(int philoPos) {
		if (philoPos >= nbBaguettes) {
			throw new IllegalArgumentException("\'position\' parameter must be less than nbBaguettes=" + nbBaguettes);
		}
		
		int leftBaguetteIndex = getLeftBaguetteIndex(philoPos);
		int rightBaguetteIndex = getRightBaguetteIndex(philoPos);

		while (!baguettesService.getBaguettes(leftBaguetteIndex, rightBaguetteIndex)) {
			Utils.sleep(100);
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp2.IBaguettesManager#rendBaguettes(int)
	 */
	public void rendBaguettes(int philoPos) {
		if (philoPos >= nbBaguettes) {
			throw new IllegalArgumentException("\'position\' parameter must be less than nbBaguettes=" + nbBaguettes);
		}
		
		int leftBaguetteIndex = getLeftBaguetteIndex(philoPos);
		int rightBaguetteIndex = getRightBaguetteIndex(philoPos);

		baguettesService.rendBaguettes(leftBaguetteIndex, rightBaguetteIndex);
	}
	
	private int getLeftBaguetteIndex(int PhiloPos) {
		return PhiloPos;
	}

	private int getRightBaguetteIndex(int philoPos) {
		return (philoPos + 1) % nbBaguettes;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.IComponentSource#getComponent()
	 */
	public BaseComponent getComponent() {
		return component;
	}
	
	private class GraphicalComponent extends BaseComponent {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			int width = getWidth();
			int height = getHeight();
			Color tableColor = new Color(200, 160, 100);
			g.setColor(tableColor);
			g.fillOval(0, 0, width-1, height-1);
			g.setColor(tableColor.darker());
			g.drawOval(0, 0, width-1, height-1);
		}
	}
}
