package monopoly;

// Modify this class in to make player's state changes observables.

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
	((MonopolyPlayer)p).sendToJail();
      }
      else { // move & replay (if not in jail)
	p.move(dicesValue);
	if (!((MonopolyPlayer)p).isPrisoner()){
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

