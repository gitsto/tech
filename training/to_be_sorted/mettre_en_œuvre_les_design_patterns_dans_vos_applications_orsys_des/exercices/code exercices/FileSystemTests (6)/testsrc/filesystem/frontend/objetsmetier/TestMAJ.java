package filesystem.frontend.objetsmetier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import filesystem.frontend.objetsmetier.impl.Dossier;
import filesystem.frontend.objetsmetier.impl.Fichier;
import filesystem.frontend.objetsmetier.intf.IDossier;
import filesystem.frontend.objetsmetier.intf.IElement;
import filesystem.frontend.objetsmetier.intf.IFichier;
import filesystem.frontend.objetsmetier.intf.ILien;
import filesystem.frontend.objetsmetier.intf.IUniteLogique;
import filesystem.shared.objetsmetier.intf.FichierHandleEvent;
import filesystem.shared.objetsmetier.intf.FichierHandleListener;
import filesystem.shared.objetsmetier.intf.IDossierHandle;
import filesystem.shared.objetsmetier.intf.IFichierHandle;
import filesystem.shared.objetsmetier.intf.IFormat;
import filesystem.shared.objetsmetier.intf.ILienHandle;

public class TestMAJ {

	private static final String NOM_DOSSIER = "nom_dossier";
	private static final String NOM_FICHIER = "nom_fichier";
	static final int TAILLE_FICHIER = 1;

	private IUniteLogique uniteLogique;
	private IDossier dossier;
	private Fichier fichier;

	private FichierHandleListener fichierHandleListener;

	class UniteLogiquePourTest implements IUniteLogique {
		@Override
		public IDossierHandle creerDossier(IDossier sousDossier) {
			return null;
		}

		@Override
		public IFichierHandle creerFichier(IFichier fichier) {
			return new IFichierHandle() {
				@Override
				public void addFichierListener(FichierHandleListener l) {
					fichierHandleListener = l;
				}
			};
		}

		@Override
		public ILienHandle creerLien(ILien lien) {
			return null;
		}

		@Override
		public void formater(IFormat format) {
		}

		@Override
		public int getDisponible() {
			return TestMAJ.TAILLE_FICHIER + 1;
		}

		@Override
		public String getNom() {
			return null;
		}

		@Override
		public boolean isFormatee() {
			return true;
		}

		@Override
		public void setDisponible() {
		}

		@Override
		public void setNom(String nom) {
		}

		private ChangeListener changeListener;
		@Override
		public void addChangeListener(ChangeListener l) {
			changeListener = l;
		}

		@Override
		public void fireStateChanged(IElement element) {
			changeListener.stateChanged(null);
		}
	}

	@Before
	public void setUp() throws Exception {
		creerUniteLogique();
		creerDossier();
		creerFichier(NOM_FICHIER, TAILLE_FICHIER);
	}

	private boolean aChange;
	private void creerUniteLogique() {
		uniteLogique = new UniteLogiquePourTest();
		uniteLogique.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				aChange = true;
			}
		});
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

	private void creerFichier(String nomFichier, int tailleFichier) {
		fichier = new Fichier(nomFichier, tailleFichier);
		dossier.ajouterFichier(fichier);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void MAJDeFichierSurDisqueVisibleDansFrontEnd() throws Exception {
		fichierHandleListener.tailleChangee(new FichierHandleEvent(fichier,
				fichier.getNom(), 3));
		assertEquals(3, fichier.getTaille());
		assertTrue("Quelque chose a change", aChange);
	}

}
