package sandbox.exercices_jva.exercice5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class MonthsInYear {

	public static void main(String[] args) {

		display(1980);
		display(1982);
	}

	public static void display(int theYear) {
		for (int month = 1; month <= 12; month++) {
			LocalDate d = LocalDate.of(theYear, month, DayOfWeek.SUNDAY.getValue());
			LocalDate firstDayOfMonth = d.with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDayOfMonth = d.with(TemporalAdjusters.lastDayOfMonth());
			int totalDaysInMonth = lastDayOfMonth.getDayOfMonth() - firstDayOfMonth.getDayOfMonth()+1;
			System.out.println("Pour l'année " + d.getYear() + ", le mois "
					+ Month.of(month).getDisplayName(TextStyle.FULL, Locale.FRANCE) + " contient " + totalDaysInMonth
					+ " jours");
		}
		System.out.println("=====================================================");
	}

}
