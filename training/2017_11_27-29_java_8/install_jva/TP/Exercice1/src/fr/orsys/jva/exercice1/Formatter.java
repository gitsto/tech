package fr.orsys.jva.exercice1;

/** Annotée pour pouvoir typer une lambda expression */
@FunctionalInterface
public interface Formatter {
	String format(String fmtString, Object ... params);
}
