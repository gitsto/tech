package monopoly.ihm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import monopoly.metier.JeuDeMonopoly;

public class Main {

	public static void main(String[] args) {
		List liste = new ArrayList();
		liste.add(new Integer(123));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("Démarrage du jeu de Monopoly\n");
		
	//	BufferedReader br = new BufferedReader(
	//			new InputStreamReader(System.in));
		
		int nbJoueurs = 0;
		int nbTours = 0;
		try {
			FileReader fr = new FileReader("data.txt");	
			BufferedReader br = new BufferedReader(fr);
		//	System.out.println("Veuillez saisir le nombre de participants");
			nbJoueurs = Integer.parseInt(br.readLine());
			
			//System.out.println("Veuillez saisir le nombre de tours");
			nbTours = Integer.parseInt(br.readLine());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		JeuDeMonopoly jeu = new JeuDeMonopoly(nbJoueurs, nbTours);
		jeu.jouer();
	}
	
	
	
}
