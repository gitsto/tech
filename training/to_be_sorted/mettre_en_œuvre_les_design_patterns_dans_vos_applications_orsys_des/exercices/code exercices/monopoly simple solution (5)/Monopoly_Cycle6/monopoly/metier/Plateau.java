package monopoly.metier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class ConfigurationProprietes {
	/*
	 * Map destinée à stocker les informations des propriétés Clé : Integer
	 * position de la case Valeur : CongigurationPropriété objet représentant
	 * les informations d'une case
	 */
	private static Map<Integer, ConfigurationPorpriete> configs;

	// Initialisation statique de la Map à partir du fichier
	static {

		configs = new HashMap<Integer, ConfigurationPorpriete>();
		try {
			// Read information from "Monopoly.txt"
			BufferedReader in = new BufferedReader(new FileReader("Monopoly.txt"));

			String line = null;
			while ((line = in.readLine()) != null) {
				ConfigurationPorpriete conf = new ConfigurationPorpriete(line);
				configs.put(new Integer(conf.position), conf);
				System.out.println("loaded : " + conf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConfigurationPorpriete get(int position) {
		return (ConfigurationPorpriete) configs.get(new Integer(position));
	}

	public static Iterator<ConfigurationPorpriete> iterator() {
		return configs.values().iterator();
	}
}

/**
 * Représentation sous forme d'objet d'une ligne du fichier Monopoly.txt
 * 
 * @author julien.carette
 */
class ConfigurationPorpriete {
	public String name;

	public int position;

	public int rent;

	public int price;

	public String group;

	public ConfigurationPorpriete(String line) {
		StringTokenizer tok = new StringTokenizer(line, ";");
		name = tok.nextToken();
		position = Integer.parseInt(tok.nextToken());
		price = Integer.parseInt(tok.nextToken());
		rent = 0;
		try {
			rent = Integer.parseInt(tok.nextToken());
		} catch (Exception e) {
		}
		group = tok.nextToken();
	}

	public String toString() {
		return "Nom:" + name + " ; Position:" + position + " ; Loyer:" + rent
				+ " ; Prix:" + price + " ; Groupe:" + group;
	}
}

public class Plateau {

	private List<Case> cases = new ArrayList<Case>();
	private Map<String, Groupe> groupes;

	public Plateau() {
		initialisationGroupes();
		initialisationCases();
		chainageCases();
	}

	private void initialisationGroupes() {
		groupes = new HashMap<String, Groupe>();
		Iterator<ConfigurationPorpriete> iterProp = ConfigurationProprietes.iterator();
		while(iterProp.hasNext()){
			ConfigurationPorpriete currentProp = iterProp.next();
			if(!groupes.containsKey(currentProp.group))
				groupes.put(currentProp.group, new Groupe(currentProp.group));
		}
		
	}

	private void initialisationCases(){
		// Initialisation du tableau de cases
		for(int i=0;i<40;i++){
			Case caseEnCourt;
			switch(i){
				case 0:
					caseEnCourt = new CaseDepart("Case départ");
					break;
				case 4:
					caseEnCourt = new CaseImpot("Case impôt");
					break;
				case 38:
					caseEnCourt = new CaseTaxeDeLuxe("Case taxe de luxe");
					break;
				default:
					ConfigurationPorpriete conf =
		                ConfigurationProprietes.get(i);
		                if (conf != null){
		                     if (conf.group.equals("Railroad")){
		                    	 caseEnCourt = new CaseGare (conf.name, conf.price, groupes.get(conf.group));
		                    }
		                    else if (conf.group.equals("Utility")){
		                    	caseEnCourt= new CaseService(conf.name, conf.price, groupes.get(conf.group));
		                    }
		                    else {
		                    	caseEnCourt = new CaseLot(conf.name, conf.price, conf.position, groupes.get(conf.group));
		                    }
		                }
		                else {
		                	caseEnCourt = new Case("Case-"+i);
		                }
					break;
				}
			cases.add(caseEnCourt);
		}
	}

	private void chainageCases() {
		// Chainage des cases
		for (int i = 0; i < 39; i++) {
			cases.get(i).setSuivante(cases.get(i + 1));
		}
		cases.get(39).setSuivante(cases.get(0));
	}

	public Case getCaseDepart() {
		return cases.get(0);
	}

}
