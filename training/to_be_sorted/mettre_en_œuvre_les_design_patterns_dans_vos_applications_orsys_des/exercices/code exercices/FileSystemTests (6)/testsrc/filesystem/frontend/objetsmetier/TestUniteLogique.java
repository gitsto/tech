package filesystem.frontend.objetsmetier;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import filesystem.frontend.objetsmetier.impl.Disque;
import filesystem.frontend.objetsmetier.impl.Dossier;
import filesystem.frontend.objetsmetier.impl.Fichier;
import filesystem.frontend.objetsmetier.impl.Lien;
import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IPartition;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;

public class TestUniteLogique {

	private IUniteLogique uniteLogique;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("filesystem.frontend.disque", "filesystem.frontend.objetsmetier.DisquePourTest");
		uniteLogique = creerUniteLogique();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void onPeutCreerUneUniteLogiqueSurUnePartition() throws Exception {
		String dernierNomAffecte = Disque.getInstance().getDernierNomAffecte();
		String nomAffecte = uniteLogique.getNom();
		assertEquals(dernierNomAffecte, nomAffecte);
	}

	private IUniteLogique creerUniteLogique() {
		IPartition partition = mock(IPartition.class);
		IUniteLogique uniteLogique = Disque.getInstance().creerUniteLogique(partition);
		return uniteLogique;
	}
	
	@Test
	public void onPeutFormaterUneUniteLogique() throws Exception {
		Disque.getInstance().format(uniteLogique, DisquePourTest.FORMAT);
		assertTrue(uniteLogique.isFormatee());
	}
	
}
