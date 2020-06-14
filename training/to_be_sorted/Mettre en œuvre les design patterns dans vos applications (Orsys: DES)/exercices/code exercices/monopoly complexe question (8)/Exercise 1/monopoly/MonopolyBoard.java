package monopoly;

public class MonopolyBoard {
    private MonopolySquare start;

    public MonopolyBoard(Monopoly game) {
        MonopolySquare s10 = new MonopolySquare(game, "s10");
        MonopolySquare s9 = new MonopolySquare(game, "s9", s10);
        MonopolySquare s8 = new MonopolySquare(game, "s8", s9);
        MonopolySquare s7 = new MonopolySquare(game, "s7", s8);
        MonopolySquare s6 = new MonopolySquare(game, "s6", s7);
        MonopolySquare s5 = new MonopolySquare(game, "s5", s6);
        MonopolySquare s4 = new MonopolySquare(game, "s4", s5);
        MonopolySquare s3 = new MonopolySquare(game, "s3", s4);
        MonopolySquare s2 = new MonopolySquare(game, "s2", s3);
        start = new MonopolySquare(game, "s1", s2);
        s10.setNext(start);
    }

    public MonopolySquare getStartSquare() {
        return start;
    }
}