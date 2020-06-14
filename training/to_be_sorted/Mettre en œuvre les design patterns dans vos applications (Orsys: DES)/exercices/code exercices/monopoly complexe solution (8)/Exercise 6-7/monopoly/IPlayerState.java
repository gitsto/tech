package monopoly;

public interface IPlayerState {
    public void singleDice(Player p, int dicesValue);
    public void doubleDice(Player p, int dicesValue);
    public void onEntry(Player p);
}