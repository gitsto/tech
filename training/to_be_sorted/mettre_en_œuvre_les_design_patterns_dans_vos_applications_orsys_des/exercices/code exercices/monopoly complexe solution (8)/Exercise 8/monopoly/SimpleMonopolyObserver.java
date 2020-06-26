package monopoly;

public class SimpleMonopolyObserver implements IMonopolyListener {

    public SimpleMonopolyObserver() {
    }
    
    public void monopolyUpdated(Monopoly m) {
        System.out.println(m);
    }
}