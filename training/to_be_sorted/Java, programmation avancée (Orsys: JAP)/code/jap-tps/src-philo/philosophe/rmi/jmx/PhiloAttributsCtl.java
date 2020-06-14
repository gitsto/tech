package philosophe.rmi.jmx;

import philosophe.thread2.actif.PhiloActif;

public class PhiloAttributsCtl implements PhiloAttributsCtlMBean {
	private PhiloActif target;
	
	public PhiloAttributsCtl(PhiloActif t) { target = t; }
	
	public long getDureeMaxManger() { if (target != null) return target.getdManger(); return 0;}	
	public void setDureeMaxManger(long d) { if (target != null) target.setdManger(d); }
	
	public long getDureeMaxReflechir() { if (target != null) return target.getdReflechir(); return 0; }
	public void setDureeMaxReflechir(long d) { if (target != null) target.setdReflechir(d); }
}
