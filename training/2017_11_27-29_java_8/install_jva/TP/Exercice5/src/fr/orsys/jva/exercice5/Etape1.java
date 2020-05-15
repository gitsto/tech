package fr.orsys.jva.exercice5;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

public class Etape1 {
	public static void main(String[] args) {
		System.out.println("Anniversaire");
		LocalDate anniversaireJ = LocalDate.of(1964, Month.NOVEMBER, 11);
		LocalDateTime anniversaireS	= LocalDateTime.of(1964, Month.NOVEMBER, 11, 19, 00);
		anniversaireS = anniversaireS.plusNanos(1);
		System.out.println(anniversaireJ);
		System.out.println(anniversaireS);
		System.out.println(anniversaireS.getNano());
		System.out.println("Date du Jeudi précédent mon anniversaire");
		LocalDate previousThursday	= anniversaireJ.with(
				TemporalAdjusters.previous(DayOfWeek.THURSDAY)
			);
		System.out.println(previousThursday);
		
		ZoneId parisId	= ZoneId.of("Europe/Paris");
		ZonedDateTime birthday	= ZonedDateTime.of(anniversaireS, parisId);
		System.out.println(birthday);
		Instant birthInstant	= birthday.toInstant();
		System.out.println(birthInstant);
		
		System.out.println(birthInstant.atZone(parisId));
	}
}
