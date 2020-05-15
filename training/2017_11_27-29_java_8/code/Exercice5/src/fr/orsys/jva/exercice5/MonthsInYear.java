package fr.orsys.jva.exercice5;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthsInYear {

	public static void display(int value) {
		try {
			Year year	= Year.of(value);
			// iteration sur les mois de l'année
			for (Month m: Month.values()) {
				YearMonth ym	= YearMonth.of(year.getValue(), m);
				System.out.println("Le mois "+m.getDisplayName(TextStyle.FULL, Locale.FRANCE)+ " a "+ ym.lengthOfMonth()+" jours");
			}
		} catch (Exception e) {
			System.err.println("L'année "+value+" ne correspond à rien");
		}
	}
}
