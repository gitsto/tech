package fr.paris.jva.exercice7;

public class EmployeException extends Exception {
	private Employe emp;

	public EmployeException(String message, Employe emp) {
		super(message);
		this.emp = emp;
	}
}