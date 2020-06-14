package monopoly;

public class FreeState implements IPlayerState {
private int nbDoubles=0;

  public void singleDice(Player p, int dicesValue)
  {
      nbDoubles=0;
      p.move(dicesValue);
  }
   public void doubleDice(Player p, int dicesValue)
   {
      nbDoubles++;
      if (nbDoubles>=3) { // go to jail !!
	ISquareIterator iterator = p.getSquareIterator();
	while (iterator.next() != ((Monopoly)(p.getGame())).getJail() ) {}
	p.setToPrisoner();
      }
      else { // move & replay (if not in jail)
	p.move(dicesValue);
	if (!p.isPrisoner()){
	    System.out.println(p.getName()+" replays ");
	    p.play();
	  }
      }
   }
   public void onEntry(Player p)
   {
      nbDoubles=0;
       System.out.println(p.getName()+" is free ");
   }

}

