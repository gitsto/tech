package filesystem.frontend.objetsmetier;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import filesystem.frontend.objetsmetier.impl.Dossier;
import filesystem.frontend.objetsmetier.impl.Fichier;
import filesystem.frontend.objetsmetier.impl.Lien;
import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElement;
import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.ILien;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;
import filesystem.frontend.objetsmetier.intf.Visitor;
import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.ILienHandle;

public class TestVisitor {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVisiteur() throws Exception {
		IDossierHandle dossierHandle = mock(IDossierHandle.class);
		IFichierHandle fichierHandle = mock(IFichierHandle.class);
		ILienHandle lienHandle = mock(ILienHandle.class);
		
		IUniteLogique uniteLogique = mock(IUniteLogique.class);
		when(uniteLogique.getDisponible()).thenReturn(10);
		when(uniteLogique.creerDossier(any(IDossier.class))).thenReturn(dossierHandle);
		when(uniteLogique.creerFichier(any(IFichier.class))).thenReturn(fichierHandle);
		when(uniteLogique.creerLien(any(ILien.class))).thenReturn(lienHandle);

		IDossier dossierParent = mock(IDossier.class);
		when(dossierParent.getUniteLogique()).thenReturn(uniteLogique);
		
		Dossier dossier = new Dossier("dossier");
		dossier.setParent(dossierParent);
		
		Fichier fichier = new Fichier("fichier", 1);
		dossier.ajouterFichier(fichier);
		
		Dossier sousDossier = new Dossier("sous_dossier");
		dossier.ajouterDossier(sousDossier);
		
		Lien lien = new Lien("lien", fichier);
		dossier.ajouterLien(lien);
		
		Fichier fichier2 = new Fichier("fichier2", 2);
		sousDossier.ajouterFichier(fichier2);
		
		
		CalculTaille taille = new CalculTaille();
		dossier.accept(taille);
		assertEquals(1 + 2, taille.taille);
	}

}
