package fr.orsys.jva.exercice1;

/** Annot�e pour pouvoir typer une lambda expression */
@FunctionalInterface
public interface Formatter {
	String format(String fmtString, Object ... params);
}
