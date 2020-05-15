package fr.paris.jva.exercice7;

import java.util.stream.Collectors;

public class Etape3 {
	public static void main(String[] args) throws EmployeException {
		Entreprise e1 = new Entreprise("8Company");
		e1.ajouter(new Employe("Bill", 15000));
		e1.ajouter(new Employe("Bob", 16000));
		e1.ajouter(new Employe("Gaston", 15700));
		e1.ajouter(new Employe("John", 14300));
		
		String result = e1.getEmployes().stream()
						.filter(x -> 15000 < x.getSalaire())
						.sorted(Employe.rule)
						.map(Object::toString)
						.collect(Collectors.joining(","));
		System.out.println(result);
	}
}
