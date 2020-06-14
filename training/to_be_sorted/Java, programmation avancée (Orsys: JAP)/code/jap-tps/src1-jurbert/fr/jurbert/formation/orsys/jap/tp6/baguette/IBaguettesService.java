package fr.jurbert.formation.orsys.jap.tp6.baguette;

public interface IBaguettesService {

	/**
	 * Warning : this call is blocking.
	 * @param philoPos
	 * @return true if the baguettes could be gotten.
	 */
	public abstract boolean getBaguettes(int bag1, int bag2);

	public abstract void rendBaguettes(int bag1, int bag2);

}