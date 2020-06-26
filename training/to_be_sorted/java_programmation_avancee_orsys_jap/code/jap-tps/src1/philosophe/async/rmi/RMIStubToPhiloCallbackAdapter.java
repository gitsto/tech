package philosophe.async.rmi;

import philosophe.api.IPhiloCallback;

public class RMIStubToPhiloCallbackAdapter implements IRMIPhiloCallback {
	private IPhiloCallback callback;

	public RMIStubToPhiloCallbackAdapter(IPhiloCallback c) { callback = c; }

	public void baguettesAllouees() { if (callback != null) callback.baguettesAllouees(); }
}
