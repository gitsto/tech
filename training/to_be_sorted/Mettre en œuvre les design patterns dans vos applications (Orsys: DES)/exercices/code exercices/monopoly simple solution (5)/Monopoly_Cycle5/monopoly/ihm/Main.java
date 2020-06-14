package monopoly.ihm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import monopoly.metier.JeuDeMonopoly;

public class Main {

	public static void main(String[] args) {
		System.out.println("Démarrage du jeu de Monopoly\n");
		
		JeuDeMonopoly jeu = chargement();
		if(jeu==null)
			jeu = new JeuDeMonopoly(2, 20);
		
		jeu.jouer();
		
		sauvegarde(jeu);
	}
	
	/**
	 * Permet une sauvegarde la partie en cours par le 
	 * biais de la sérialisation
	 * @param jeu
	 */
	private static void sauvegarde(JeuDeMonopoly jeu){
		try {
			FileOutputStream fos = new FileOutputStream("partie.ser"); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(jeu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static JeuDeMonopoly chargement(){
		JeuDeMonopoly jeu=null;
		try {
			FileInputStream fis = new FileInputStream("partie.ser"); 
			ObjectInputStream ois = new ObjectInputStream(fis);
			jeu = (JeuDeMonopoly)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jeu;
	}
	
}
