package fr.jurbert.formation.orsys.jap.tp6.baguette;

import fr.jurbert.formation.orsys.jap.tp6.gui.Gui;
import fr.jurbert.formation.orsys.jap.tp6.rmi.RemoteRMIBaguettesService;

public final class BaguettesServiceFactory {

	private final static boolean REMOTE_SERVER = true;

	private final static boolean RMI = true;

	private BaguettesServiceFactory() {
	}
	
	public static boolean isRemote() {
		return REMOTE_SERVER;
	}
	
	public static IBaguettesService getBaguettesService(int nbBaguettes) {
		final IBaguettesService baguettesService;

		if (REMOTE_SERVER) {
			if (RMI) {
				baguettesService = new RemoteRMIBaguettesService();
			} else {
				baguettesService = new RemoteSocketBaguettesService(nbBaguettes);
			}
		} else {
			Baguette[] baguettes = new Baguette[nbBaguettes];
			for (int i = 0; i < baguettes.length; i++) {
				Baguette baguette = new Baguette();
				baguettes[i] = baguette;
				Gui.getInstance().addBaguette(baguette, i);
			}
			baguettesService = new LocalBaguettesService(baguettes);
		}

		return baguettesService;
	}
}
