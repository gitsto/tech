package monopoly;

public class MonopolyBoard {
    private MonopolySquare start;
    private MonopolySquare jail;
    public MonopolyBoard(Monopoly game) {
        //creation of the 40 squares
        PropertySquare s40 = new PropertySquare(game, "BoardWalk", 400, 50);
        LuxuryTaxSquare s39 = new LuxuryTaxSquare(game, "Luxury Tax", s40, 75);
        PropertySquare s38 = new PropertySquare(game, "Park Place", s39, 350, 35);
        CardSquare s37 = new CardSquare(game, "Chance", s38);
        PropertySquare s36 = new PropertySquare(game, "Short Line Railroad",s37, 200, 50); //Railroad
        PropertySquare s35 = new PropertySquare(game, "Pennsylvania Avenue", s36, 320, 28);
        CardSquare s34 = new CardSquare(game, "Community", s35);
        PropertySquare s33 = new PropertySquare(game, "North Carolina Avenue", s34, 300, 26);
        PropertySquare s32 = new PropertySquare(game, "Pacific Avenue", s33, 300, 26);
        GoToJailSquare s31 = new GoToJailSquare(game, "Go to Jail", s32);
        PropertySquare s30 = new PropertySquare(game, "Marvin Garden", s31, 280, 22);
        PropertySquare s29 = new PropertySquare(game, "Water Works",s30, 200, 50); //Company
        PropertySquare s28 = new PropertySquare(game, "Ventnor Avenue", s29, 260, 22);
        PropertySquare s27 = new PropertySquare(game, "Atlantic Avenue", s28, 260, 22);
        PropertySquare s26 = new PropertySquare(game, "B. & O. Railroad",s27, 200, 50); //Railroad
        PropertySquare s25 = new PropertySquare(game, "Illinois Avenue", s26, 240, 20);
        PropertySquare s24 = new PropertySquare(game, "Indiana Avenue", s25, 220, 18);
        CardSquare s23 = new CardSquare(game, "Chance", s24);
        PropertySquare s22 = new PropertySquare(game, "Kentucky Avenue", s23, 220, 18);
        FreeParkSquare s21 = new FreeParkSquare(game, "Free Park", s22);
        PropertySquare s20 = new PropertySquare(game, "New Tork Avenue", s21, 200, 16);
        PropertySquare s19 = new PropertySquare(game, "Tennessee Avenue", s20, 180, 14);
        CardSquare s18 = new CardSquare(game, "Community", s19);
        PropertySquare s17 = new PropertySquare(game, "St James Place", s18, 180, 14);
        PropertySquare s16 = new PropertySquare(game, "Pennsylvania Railroad",s17, 200, 50); //Railroad
        PropertySquare s15 = new PropertySquare(game, "Virginia Avenue", s16, 160, 12);
        PropertySquare s14 = new PropertySquare(game, "States Avenue", s15, 140, 10);
        PropertySquare s13 = new PropertySquare(game, "Electric Company",s14, 200, 50); //Company
        PropertySquare s12 = new PropertySquare(game, "St Charles Place", s13, 140, 10);
        MonopolySquare s11 = new MonopolySquare(game, "Jail", s12); //Jail
        PropertySquare s10 = new PropertySquare(game, "Connecticut Avenue", s11, 120, 8);
        PropertySquare s9 = new PropertySquare(game, "Vermont Avenue", s10, 100, 6);
        CardSquare s8 = new CardSquare(game, "Chance", s9);
        PropertySquare s7 = new PropertySquare(game, "Oriental Avenue", s8, 100, 6);
        PropertySquare s6 = new PropertySquare(game, "Reading Railroad",s7, 200, 50); //Railroad
        IncomeTaxSquare s5 = new IncomeTaxSquare(game, "Income Tax", s6, 200);
        PropertySquare s4 = new PropertySquare(game, "Baltic Avenue", s5, 60, 4);
        CardSquare s3 = new CardSquare(game, "Community", s4);
        PropertySquare s2 = new PropertySquare(game, "Mediterranean Avenue", s3, 60, 2);
        StartSquare s1 = new StartSquare(game, "Start", s2, 200);
        s40.setNext(s1);
        start = s1;
        jail = s11;
    }

    public MonopolySquare getStartSquare() {
        return start;
    }
    public MonopolySquare getJail() {
        return jail;
    }
}