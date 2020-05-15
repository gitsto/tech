package fr.orsys.jva.exercice5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class ListMondays {

	public static void display(int yearValue, String monthValue) {
		try {
			Year year		= Year.of(yearValue);
			Month month		= Month.valueOf(monthValue.toUpperCase(Locale.FRANCE));
			System.out.println("Pour le mois "+monthValue+" de l'ann�e "+yearValue);
			YearMonth ym	= year.atMonth(month);
			LocalDate localDate	= ym.atDay(1);	// 1er jour du mois
			localDate			= localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));	// 1er Lundi
			do {
				System.out.println(localDate);
				localDate	=  localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			} while (localDate.getMonth().equals(month));
			
		} catch (Exception e) {
			System.err.println("L'ann�e "+yearValue+" ne correspond � rien avec le mois "+monthValue);
		}
	}

}
