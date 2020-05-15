package fr.orsys.jva.exercice5;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class FridayThirteenQuery {

	public static boolean query(LocalDateTime today) {
		boolean resultat	= (today.getLong(ChronoField.DAY_OF_MONTH) == 13)
				&& (today.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.FRIDAY.getValue());
		return resultat;
	}

}
