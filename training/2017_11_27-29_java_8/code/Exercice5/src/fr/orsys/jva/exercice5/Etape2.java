package fr.orsys.jva.exercice5;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;


public class Etape2 {
	public static void main(String[] args) throws Exception  {
		Scanner clavier = new Scanner(System.in);
		System.out.print("Entrez une année ");
		int year		= clavier.nextInt();
		MonthsInYear.display(year);
		System.out.print("Entrez un mois ");
		String month	= clavier.next();
		ListMondays.display(year, month);
		clavier.close();
		LocalDateTime badDay	= LocalDateTime.parse("2015-03-13T00:00:00");
		
		if (FridayThirteenQuery.query(badDay))
			System.out.println("Danger");
		else
			System.out.println("No danger");
		
		System.out.print("Calcul du nombre de jours qui nous sépare du 13 mars 2015: ");
		
		LocalDateTime today	= LocalDateTime.now();
		Period difference	= Period.between(badDay.toLocalDate(), today.toLocalDate());
		MessageFormat mfmt	= new MessageFormat("P{0, number}M{1, number}D");
		Object[] results	= mfmt.parse(difference.normalized().toString());
		System.out.println(results[0]+ " mois et " + results[1] +" jours");
	}
}
