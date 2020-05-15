package fr.paris.jva.exercice7;


public class Etape1 {
	public static void main(String[] args) throws EmployeException {
		Entreprise e1 = new Entreprise("8Company");
		e1.ajouter(new Employe("Bill", 15000));
		e1.ajouter(new Employe("Bob", 16000));
		e1.ajouter(new Employe("Gaston", 15700));
		e1.ajouter(new Employe("John", 14300));
		System.out.println(e1);
		
		// Tri par salaires croissants
		System.out.println("Employés de " + e1.getNom()	+ " par ordre croissant des salaires");
		e1.getEmployes().stream()
						.sorted(Employe.rule)
						.forEach(System.out::println);
	}
}