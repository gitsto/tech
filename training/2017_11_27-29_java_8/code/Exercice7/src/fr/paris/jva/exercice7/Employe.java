package fr.paris.jva.exercice7;

import java.util.Comparator;

public class Employe {
	/** Une comparaison possible entre employés */
	public static Comparator<Employe> rule	= (emp1, emp2) -> Double.compare(emp1.getSalaire(), emp2.getSalaire());
	private String nom;
	private double salaire;

	protected Employe(String nom, double salaire) {
		this.nom = nom;
		this.salaire = salaire;
	}

	public String getNom() {
		return nom;
	}

	public double getSalaire() {
		return salaire;
	}

	@Override
	public String toString() {
		return "Employe [nom=" + nom + ", salaire=" + salaire + "]";
	}
	
}