package filesystem.shared.objetsmetier.intf;

import java.util.EventListener;

public interface FichierHandleListener extends EventListener{
	void tailleChangee(FichierHandleEvent evt);
}
