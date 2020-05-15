package fr.orsys.jva.exercice1;

import java.util.function.Supplier;

public interface DefaulableFactory {
	// le param�tre est une reference � une m�thode notRequired
	// i.e. DefaulableImpl::notRequired ou OverridableImpl::notRequired
	static Defaulable create(Supplier<Defaulable> supplier) {
		return supplier.get();
	}
}
