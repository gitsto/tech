package monopoly;

public interface ISquare {
    public void passOverBy(Player p);
    public void landOnBy(Player p);
    public String getName();
}