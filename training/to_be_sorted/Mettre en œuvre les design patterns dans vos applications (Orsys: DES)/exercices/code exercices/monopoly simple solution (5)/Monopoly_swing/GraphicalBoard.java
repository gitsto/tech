import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicalBoard extends JFrame {
  private static String baseDirectory = "Monopoly/Cases/";
  private static String[] squares = {
    "ParcGratuit", "Matignon", "ChanceHaut", "Malesherbe", "HenriMartin",
    "GareDuNord", "SaintHonore", "Bourse", "CompagnieEaux", "LaFayette",
    "AllezEnPrison",

    "Pigalle",
    "", "", "", "", "", "", "", "", "",
    "Breteuil",

    "SaintMichel",
    "", "", "", "", "", "", "", "", "",
    "Foch",

    "CaisseCommunauteGauche",
    "", "", "", "", "", "", "", "", "",
    "CaisseCommunauteDroite",

    "Mozart",
    "", "", "", "", "", "", "", "", "",
    "Capucines",

    "GareLyon",
    "", "", "", "", "", "", "", "", "",
    "GareSaintLazarre",

    "Paradis",
    "", "", "", "", "", "", "", "", "",
    "ChanceDroite",

    "Neuilly",
    "", "", "", "", "", "", "", "", "",
    "ChampsElysees",

    "CompagnieElectricite",
    "", "", "", "", "", "", "", "", "",
    "TaxeLuxe",

    "Villette",
    "", "", "", "", "", "", "", "", "",
    "RuePaix",

    "Prison", "Republique", "Courcelles", "ChanceBas", "Vaugirard",
    "GareMontparnasse", "Impots", "Lecourbe", "CaisseCommunauteBas",
    "Belleville", "Depart",
  };

  public GraphicalBoard() {
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(11, 11));

    int len = squares.length;
    for (int i = 0; i < len; i++) {
      String label = squares[i];
      if ("".equals(label)){
        pane.add(new JLabel(" "));
      }
      else{
        pane.add(new JLabel(new ImageIcon(baseDirectory + label + ".jpg")));
      }
    }

    setVisible(true);
    pack();
  }

  static public void main(String[] args) {
    new GraphicalBoard();
  }
}
