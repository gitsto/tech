package philosophe.base;

public abstract class Philosophe {
	private int numero;
    private String nom;
    
    private int NBROUND=100;
    private long dReflechir;
    private long dManger;
    
    public Philosophe(String nm, int nu, long dR, long dM) {
    	nom = nm;
    	numero=nu;
    	dReflechir = dR;
    	dManger = dM;
    }
    
    public Philosophe(int nu) { this("philo" + nu, nu, 1000, 1000); }
    
	public int getNumero() { return numero; }
	public String getNom() { return nom; }	
	
    public int getNBROUND() { return NBROUND; }
	public void setNBROUND(int nBROUND) { NBROUND = nBROUND; }

	public long getdReflechir() { return dReflechir; }
	public void setdReflechir(long dR) { dReflechir = dR; }
	public long getdManger() { return dManger; }
	public void setdManger(long dM) { dManger = dM; }
}
