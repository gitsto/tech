package fr.paris.jva.exercice7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Entreprise implements Iterable<Employe> {
	private String nom;
	private List<Employe> employes;

	public Entreprise(String nom) {
		this.nom = nom;
		employes = new ArrayList<Employe>();
	}

	public String getNom() {
		return nom;
	}

	public List<Employe> getEmployes() {
		return Collections.unmodifiableList(employes);
	}

	public void ajouter(Employe emp) throws EmployeException {
		if (!employes.add(emp)) {
			throw new EmployeException("Employé déjà dans cette entreprise",
					emp);
		}
	}

	@Override
	public Iterator<Employe> iterator() {
		return employes.iterator();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(nom);
		for (Employe employe : employes) {
			sb.append("\n " + employe.getNom());
		}
		return sb.toString();
	}
}