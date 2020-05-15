/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.testexamenhtmlunit;

import static junit.framework.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 *
 * @author Administrateur
 */
@RunWith(Parameterized.class)
public class TestExamen {

	private final String cours;
	private final String examen;
	private final String attendu;

	@Parameterized.Parameters
	public static Collection testsData() throws IOException {
		InputStream feuilleExcel = new FileInputStream("examen-data.xls");
		return new FeuilleExcel(feuilleExcel).getData();
	}

	public TestExamen(double cours, double examen, String attendu) {
		this.cours = new Integer((int) cours).toString();
		this.examen = new Integer((int) examen).toString();
		;
		this.attendu = attendu;
	}

	@Test
	@SuppressWarnings("empty-statement")
	public void testNiveau() throws Exception {
		try (final WebClient webClient = new WebClient()) {

			// Get the first page
			final HtmlPage page1 = webClient.getPage("http://localhost:8080/ato-1.0-SNAPSHOT/formulaire-notes.jsp");

			// Get the form that we are dealing with and within that form,
			// find the submit button and the field that we want to change.
			final HtmlForm form = page1.getForms().get(0);

			final HtmlSubmitInput button = form.getInputByValue("Evaluer le niveau");
			final HtmlTextInput textExamen = form.getInputByName("examen");
			final HtmlTextInput textCours = form.getInputByName("cours");

			// Change the value of the text field
			textExamen.setValueAttribute(examen);
			textCours.setValueAttribute(cours);

			// Now submit the form by clicking the button and get back the second page.
			final HtmlPage pageResultat = button.click();
			HtmlDivision div = pageResultat.getHtmlElementById("niveau");

			String resultatObtenu = div.getTextContent();
			assertEquals(attendu, resultatObtenu);

		}
	}

}
