package filesystem.frontend.objetsmetier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import filesystem.frontend.objetsmetier.impl.Dossier;
import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElementReferencable;
import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.ILien;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;
import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.IFormat;
import filesystem.shared.objetsmetier.intf.ILienHandle;

public class TestDossier {

	private static final String NOM_DOSSIER = "nom_dossier";
	private static final String NOM_FICHIER = "nom_fichier";
	private static final int TAILLE_FICHIER = 1;
	private static final String NOM_LIEN = "nom_lien";

	private Dossier dossier;
	private IUniteLogique uniteLogique;

	@Before
	public void setUp() throws Exception {
		creerUniteLogique();
		creerDossier();
	}

	private void creerUniteLogique() {
		 uniteLogique = mock(IUniteLogique.class);
	}

	private IDossier creerDossierParent() {
		IDossier dossierParent = mock(IDossier.class);
		when(dossierParent.getUniteLogique()).thenReturn(uniteLogique);
		return dossierParent;
	}

	private void creerDossier() {
		IDossier dossierParent = creerDossierParent();
		dossier = new Dossier(NOM_DOSSIER);
		dossier.setParent(dossierParent);
	}

	private Dossier creerSousDossier(String nomDossier) {
		Dossier sousDossier = new Dossier(nomDossier);
		return sousDossier;
	}

	private IFichier creerFichier(String nomFichier, int tailleFichier) {
		IFichier fichier = mock(IFichier.class);
		when(fichier.getNom()).thenReturn(nomFichier);
		when(fichier.getTaille()).thenReturn(tailleFichier);
		return fichier;
	}

	private ILien creerLien(String nomLien,
			IElementReferencable elementReferencable) {
		ILien lien = mock(ILien.class);
		when(lien.getNom()).thenReturn(nomLien);
		when(lien.getElementReferencable()).thenReturn(elementReferencable);
		return lien;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void unNouveauDossierEstVide() throws Exception {
		assertEquals(0, dossier.getTaille());
	}

	@Test
	public void onPeutAjouterUnFichierAUnDossier() throws Exception {
		when(uniteLogique.getDisponible()).thenReturn(TAILLE_FICHIER + 1);
		IFichier fichier = creerFichier(NOM_FICHIER, TAILLE_FICHIER);
		dossier.ajouterFichier(fichier);
		assertEquals(TAILLE_FICHIER, dossier.getTaille());
		verify(fichier).setParent(dossier);
	}

	@Test
	public void onPeutAjouterUnLienAUnDossier() throws Exception {
		when(uniteLogique.getDisponible()).thenReturn(0);
		ILien lien = creerLien(NOM_LIEN, dossier);
		dossier.ajouterLien(lien);
		assertEquals(0, dossier.getTaille());
		verify(lien).setParent(dossier);
	}

	@Test
	public void onPeutAjouterUnDossierAUnDossier() throws Exception {
		when(uniteLogique.getDisponible()).thenReturn(0);
		Dossier sousDossier = creerSousDossier("sous_dossier");
		dossier.ajouterDossier(sousDossier);
		assertEquals(0, dossier.getTaille());
		assertSame(dossier, sousDossier.getParent());
	}

	@Test(expected = IllegalArgumentException.class)
	public void uneUniteLogiqueDoitAvoirUnEspaceDisponiblePourUnAjout()
			throws Exception {
		when(uniteLogique.getDisponible()).thenReturn(TAILLE_FICHIER - 1);
		IFichier fichier = creerFichier(NOM_FICHIER, TAILLE_FICHIER);
		dossier.ajouterFichier(fichier);
	}

	@Test(expected = IllegalArgumentException.class)
	public void onNePeutPasAjouterUnElementDeNomDuplique() throws Exception {
		when(uniteLogique.getDisponible()).thenReturn(TAILLE_FICHIER * 3);
		IFichier fichier = creerFichier(NOM_FICHIER, TAILLE_FICHIER);
		dossier.ajouterFichier(fichier);
		IFichier fichier2 = creerFichier(NOM_FICHIER, TAILLE_FICHIER);
		dossier.ajouterFichier(fichier2);
	}

}
