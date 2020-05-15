package fr.orsys.jva.exercice1;

public interface Defaulable {
	default String notRequired() {
		return "Default implementation";
	}
}
