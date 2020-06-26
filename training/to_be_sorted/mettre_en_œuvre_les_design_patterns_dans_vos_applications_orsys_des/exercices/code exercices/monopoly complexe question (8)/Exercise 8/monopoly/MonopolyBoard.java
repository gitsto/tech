package monopoly;

public class MonopolyBoard {
    private MonopolySquare start;
    private MonopolySquare jail;
    public MonopolyBoard(Monopoly game) {
	// create the  groups
	LotGroup grpPurple = new LotGroup("Purple");
	LotGroup grpLightGreen = new LotGroup("LightGreen");
	LotGroup grpViolet = new LotGroup("Violet");
	LotGroup grpOrange = new LotGroup("Orange");
	LotGroup grpRed = new LotGroup("Red");
	LotGroup grpYellow = new LotGroup("Yellow");
	LotGroup grpDarkGreen = new LotGroup("DarkGreen");
	LotGroup grpBlue = new LotGroup("Blue");
	StationGroup grpStation = new StationGroup("Station");
	CompanyGroup grpCompany = new CompanyGroup("Company");

	//creation of the 40 squares
	PropertySquare s40 = new PropertySquare(game, "BoardWalk", 400, 50, grpBlue);
	LuxuryTaxSquare s39 = new LuxuryTaxSquare(game, "Luxury Tax", s40, 75);
	PropertySquare s38 = new PropertySquare(game, "Park Place", s39, 350, 35, grpBlue);
	CardSquare s37 = new CardSquare(game, "Chance", s38);
	PropertySquare s36 = new PropertySquare(game, "Short Line Railroad",s37, 200, 50, grpStation); //Station
	PropertySquare s35 = new PropertySquare(game, "Pennsylvania Avenue", s36, 320, 28, grpDarkGreen);
	CardSquare s34 = new CardSquare(game, "Community", s35);
	PropertySquare s33 = new PropertySquare(game, "North Carolina Avenue", s34, 300, 26, grpDarkGreen);
	PropertySquare s32 = new PropertySquare(game, "Pacific Avenue", s33, 300, 26, grpDarkGreen);
	GoToJailSquare s31 = new GoToJailSquare(game, "Go to Jail", s32);
	PropertySquare s30 = new PropertySquare(game, "Marvin Garden", s31, 280, 22, grpYellow);
	CompanySquare s29 = new CompanySquare(game, "Water Works",s30, 200, 50, grpCompany); //Company
	PropertySquare s28 = new PropertySquare(game, "Ventnor Avenue", s29, 260, 22, grpYellow);
	PropertySquare s27 = new PropertySquare(game, "Atlantic Avenue", s28, 260, 22, grpYellow);
	PropertySquare s26 = new PropertySquare(game, "B. & O. Railroad",s27, 200, 50, grpStation); //Railroad
	PropertySquare s25 = new PropertySquare(game, "Illinois Avenue", s26, 240, 20, grpRed);
	PropertySquare s24 = new PropertySquare(game, "Indiana Avenue", s25, 220, 18, grpRed);
	CardSquare s23 = new CardSquare(game, "Chance", s24);
	PropertySquare s22 = new PropertySquare(game, "Kentucky Avenue", s23, 220, 18, grpRed);
	FreeParkSquare s21 = new FreeParkSquare(game, "Free Park", s22);
	PropertySquare s20 = new PropertySquare(game, "New Tork Avenue", s21, 200, 16, grpOrange);
	PropertySquare s19 = new PropertySquare(game, "Tennessee Avenue", s20, 180, 14, grpOrange);
	CardSquare s18 = new CardSquare(game, "Community", s19);
	PropertySquare s17 = new PropertySquare(game, "St James Place", s18, 180, 14, grpOrange);
	PropertySquare s16 = new PropertySquare(game, "Pennsylvania Railroad",s17, 200, 50, grpStation); //Station
	PropertySquare s15 = new PropertySquare(game, "Virginia Avenue", s16, 160, 12, grpViolet);
	PropertySquare s14 = new PropertySquare(game, "States Avenue", s15, 140, 10, grpViolet);
	CompanySquare s13 = new CompanySquare(game, "Electric Company",s14, 200, 50, grpCompany); //Company
	PropertySquare s12 = new PropertySquare(game, "St Charles Place", s13, 140, 10, grpViolet);
	MonopolySquare s11 = new MonopolySquare(game, "Jail", s12); //Jail
	PropertySquare s10 = new PropertySquare(game, "Connecticut Avenue", s11, 120, 8, grpLightGreen);
	PropertySquare s9 = new PropertySquare(game, "Vermont Avenue", s10, 100, 6, grpLightGreen);
	CardSquare s8 = new CardSquare(game, "Chance", s9);
	PropertySquare s7 = new PropertySquare(game, "Oriental Avenue", s8, 100, 6, grpLightGreen);
	PropertySquare s6 = new PropertySquare(game, "Reading Railroad",s7, 200, 50, grpStation); //Station
	IncomeTaxSquare s5 = new IncomeTaxSquare(game, "Income Tax", s6, 200);
	PropertySquare s4 = new PropertySquare(game, "Baltic Avenue", s5, 60, 4, grpPurple);
	CardSquare s3 = new CardSquare(game, "Community", s4);
	PropertySquare s2 = new PropertySquare(game, "Mediterranean Avenue", s3, 60, 2, grpPurple);
	StartSquare s1 = new StartSquare(game, "Start", s2, 200);
	s40.setNext(s1);
	start = s1;
	jail = s11;

	// adding squares to groups
	grpPurple.add(s2); grpPurple.add(s4);
	grpStation.add(s6); grpStation.add(s16); grpStation.add(s26); grpStation.add(s36);
	grpLightGreen.add(s7); grpLightGreen.add(s9); grpLightGreen.add(s10);
	grpViolet.add(s12); grpViolet.add(s14); grpViolet.add(s15);
	grpCompany.add(s13); grpCompany.add(s29);
	grpOrange.add(s17); grpOrange.add(s19); grpOrange.add(s20);
	grpRed.add(s22); grpRed.add(s24); grpRed.add(s25);
	grpYellow.add(s27);  grpYellow.add(s28); grpYellow.add(s30);
	grpDarkGreen.add(s32); grpDarkGreen.add(s33); grpDarkGreen.add(s35);
	grpBlue.add(s38); grpBlue.add(s40);
    }

    public MonopolySquare getStartSquare() {
	return start;
    }
    public MonopolySquare getJail() {
	return jail;
    }
}