package monopoly;

import java.util.Random;
import java.util.Vector;
import java.util.Enumeration;

public class Monopoly implements IGameBoard, Runnable {
    private Random dice = new Random();
    private boolean doubleDice;
    private int diceTotal;
    private int roundnb;
    private int round = 0;
    private int boardMoney = 0;
    private MonopolySquare start;
    private Vector players = new Vector();
    private Vector observers = new Vector();
    private boolean stop = true;

    public Monopoly(int roundnb) {
        MonopolyBoard b = new MonopolyBoard(this);
        start = b.getStartSquare();
        this.roundnb = roundnb;
    }

    public int throwDices() {
        doubleDice = false;
        int v1 = dice.nextInt(6) + 1;
        int v2 = dice.nextInt(6) + 1;
        if (v1 == v2)
            doubleDice = true;
        diceTotal = v1 + v2;
        notifyObservers("monopolyUpdated");
        return diceTotal;
    }

    public ISquareIterator createIterator() {
        return new MonopolySquareIterator(start);
    }

    public boolean doubleDice() {
        return doubleDice;
    }

    public int getDiceTotal() {
        return diceTotal;
    }

    public int takeBoardMoney() {
        int credit = boardMoney;
        boardMoney = 0;
        notifyObservers("monopolyUpdated");
        return credit;
    }

    public void addBoardMoney(int money) {
        boardMoney += money;
        notifyObservers("monopolyUpdated");
    }

    public void addPlayer(Player p) {
        if (!players.contains(p)) players.addElement(p);
    }

    public void removePlayers() {
        players.removeAllElements();
    }

    public void run() {
        while (!stop)
            try {
                nextRound();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void stopGame() {
        stop = true;
    }

    public void startGame() {
        Thread t = new Thread(this);
        stop = false;
        t.start();
    }

    public void nextRound() {
        if (++round > roundnb)
            stopGame();
        else
            for (Enumeration e = players.elements(); e.hasMoreElements(); )
                ((Player) e.nextElement()).play();
    }

    public String toString() {
        return new String ("Round" + round + ", Dice = " + diceTotal + ", double? " + doubleDice + ", board money = " + boardMoney);
    }

    public void addMonopolyListener(IMonopolyListener l) {
        if (!observers.contains(l))
            observers.addElement(l);
    }

    public void removeMonopolyListener(IMonopolyListener l) {
        observers.removeElement(l) ;
    }

    public void removeMonopolyListeners() {
        observers.removeAllElements();
    }

    //Example of reflection usage to notify observers with different operations
    public void notifyObservers(String methodName) {
        try {
            Class c = Class.forName("monopoly.IMonopolyListener");
            Class p = Class.forName("monopoly.Monopoly");
            java.lang.reflect.Method m = c.getDeclaredMethod(methodName, new Class[] {p});
            for (Enumeration e = observers.elements(); e.hasMoreElements(); )
                m.invoke((IMonopolyListener) e.nextElement(), new Object[] {this});
        }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }
}
