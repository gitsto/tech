package monopoly.ihm;

import monopoly.metier.JeuDeMonopoly;

public class Main {

	public static void main(String[] args) {
		System.out.println("D�marrage du jeu de Monopoly\n");
		
		JeuDeMonopoly jeu = new JeuDeMonopoly(2, 20);
		jeu.jouer();
	}
	
}
