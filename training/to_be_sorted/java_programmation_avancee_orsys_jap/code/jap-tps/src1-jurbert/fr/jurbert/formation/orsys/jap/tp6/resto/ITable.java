package fr.jurbert.formation.orsys.jap.tp6.resto;

import fr.jurbert.formation.orsys.jap.tp6.gui.IComponentSource;

public interface ITable extends IComponentSource {

	public Plate getPlate(int philoPos);
	
	public void getBaguettes(int philoPos);

	public void rendBaguettes(int philoPos);

}