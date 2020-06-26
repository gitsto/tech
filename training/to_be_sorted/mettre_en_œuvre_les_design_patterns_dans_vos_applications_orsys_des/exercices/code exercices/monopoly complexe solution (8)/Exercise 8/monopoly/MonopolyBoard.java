package monopoly;

public class MonopolyBoard {
    private MonopolySquare start;
    private MonopolySquare jail;
    public MonopolyBoard(Monopoly game) {
	// create the  groups
	PropertyGroup grpPurple = new PropertyGroup("Purple");
	PropertyGroup grpLightGreen = new PropertyGroup("LightGreen");
	PropertyGroup grpViolet = new PropertyGroup("Violet");
	PropertyGroup grpOrange = new PropertyGroup("Orange");
	PropertyGroup grpRed = new PropertyGroup("Red");
	PropertyGroup grpYellow = new PropertyGroup("Yellow");
	PropertyGroup grpDarkGreen = new PropertyGroup("DarkGreen");
	PropertyGroup grpBlue = new PropertyGroup("Blue");
	PropertyGroup grpStation = new PropertyGroup("Station");
	PropertyGroup grpCompany = new PropertyGroup("Company");

	//creation of the 40 squares
	LotSquare s40 = new LotSquare(game, "BoardWalk", 400, 50, grpBlue);
	LuxuryTaxSquare s39 = new LuxuryTaxSquare(game, "Luxury Tax", s40, 75);
	LotSquare s38 = new LotSquare(game, "Park Place", s39, 350, 35, grpBlue);
	CardSquare s37 = new CardSquare(game, "Chance", s38);
	StationSquare s36 = new StationSquare(game, "Short Line Railroad",s37, 200, 50, grpStation); //Station
	LotSquare s35 = new LotSquare(game, "Pennsylvania Avenue", s36, 320, 28, grpDarkGreen);
	CardSquare s34 = new CardSquare(game, "Community", s35);
	LotSquare s33 = new LotSquare(game, "North Carolina Avenue", s34, 300, 26, grpDarkGreen);
	LotSquare s32 = new LotSquare(game, "Pacific Avenue", s33, 300, 26, grpDarkGreen);
	GoToJailSquare s31 = new GoToJailSquare(game, "Go to Jail", s32);
	LotSquare s30 = new LotSquare(game, "Marvin Garden", s31, 280, 22, grpYellow);
	CompanySquare s29 = new CompanySquare(game, "Water Works",s30, 200, 50, grpCompany); //Company
	LotSquare s28 = new LotSquare(game, "Ventnor Avenue", s29, 260, 22, grpYellow);
	LotSquare s27 = new LotSquare(game, "Atlantic Avenue", s28, 260, 22, grpYellow);
	StationSquare s26 = new StationSquare(game, "B. & O. Railroad",s27, 200, 50, grpStation); //Station
	LotSquare s25 = new LotSquare(game, "Illinois Avenue", s26, 240, 20, grpRed);
	LotSquare s24 = new LotSquare(game, "Indiana Avenue", s25, 220, 18, grpRed);
	CardSquare s23 = new CardSquare(game, "Chance", s24);
	LotSquare s22 = new LotSquare(game, "Kentucky Avenue", s23, 220, 18, grpRed);
	FreeParkSquare s21 = new FreeParkSquare(game, "Free Park", s22);
	LotSquare s20 = new LotSquare(game, "New Tork Avenue", s21, 200, 16, grpOrange);
	LotSquare s19 = new LotSquare(game, "Tennessee Avenue", s20, 180, 14, grpOrange);
	CardSquare s18 = new CardSquare(game, "Community", s19);
	LotSquare s17 = new LotSquare(game, "St James Place", s18, 180, 14, grpOrange);
	StationSquare s16 = new StationSquare(game, "Pennsylvania Railroad",s17, 200, 50, grpStation); //Station
	LotSquare s15 = new LotSquare(game, "Virginia Avenue", s16, 160, 12, grpViolet);
	LotSquare s14 = new LotSquare(game, "States Avenue", s15, 140, 10, grpViolet);
	CompanySquare s13 = new CompanySquare(game, "Electric Company",s14, 200, 50, grpCompany); //Company
	LotSquare s12 = new LotSquare(game, "St Charles Place", s13, 140, 10, grpViolet);
	MonopolySquare s11 = new MonopolySquare(game, "Jail", s12); //Jail
	LotSquare s10 = new LotSquare(game, "Connecticut Avenue", s11, 120, 8, grpLightGreen);
	LotSquare s9 = new LotSquare(game, "Vermont Avenue", s10, 100, 6, grpLightGreen);
	CardSquare s8 = new CardSquare(game, "Chance", s9);
	LotSquare s7 = new LotSquare(game, "Oriental Avenue", s8, 100, 6, grpLightGreen);
	StationSquare s6 = new StationSquare(game, "Reading Railroad",s7, 200, 50, grpStation); //Railroad
	IncomeTaxSquare s5 = new IncomeTaxSquare(game, "Income Tax", s6, 200);
	LotSquare s4 = new LotSquare(game, "Baltic Avenue", s5, 60, 4, grpPurple);
	CardSquare s3 = new CardSquare(game, "Community", s4);
	LotSquare s2 = new LotSquare(game, "Mediterranean Avenue", s3, 60, 2, grpPurple);
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