package filesystem.frontend.objetsmetier.impl;

import filesystem.frontend.objetsmetier.intf.IDisque;

public abstract class Disque implements IDisque {
	private static IDisque disque;

	protected Disque() {
	}
	
	public static IDisque getInstance() {
		if (disque == null) {
            String className = System.getProperty("filesystem.frontend.disque", "filesystem.frontend.objetsmetier.impl.DisqueImpl");
			try {
				disque = (IDisque) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return disque;
	}

}
