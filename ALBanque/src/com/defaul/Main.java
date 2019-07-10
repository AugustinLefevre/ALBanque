
package com.defaul;
import java.util.Collections;
import java.util.Hashtable;
import com.composants.*;

public class Main {

	private static Client[] collection = generateClient(2);
	private static Compte[] comptCollection = generateCompte(collection);
	private static Hashtable hashtable = getHashtable(comptCollection);
	
	public static void main(String[] args) {
		//1.1.1 Création de la classe client
		showClient(collection);
		//1.2.3 Création du tableau des comptes
		showCompte(comptCollection);
		//1.3.1 Adaptation du tableau des comptes
		showHashtable(hashtable);
	}
	
	public static Client[] generateClient(int nbGenerate) {
		Client[] tab = new Client[nbGenerate];
		for(int i = 0; i < nbGenerate; i++) {
			String name = "nom" + (i + 1) ;
			String lastname = "prenom" + (i + 1);
			tab[i] = new Client(name, lastname);
		}
		return tab;
	}
	public static void showClient(Client[] tab) {
		for(Client cli : tab) {
			System.out.println(cli.toString());
		}
	}
	public static Compte[] generateCompte(Client[] clients) {
		Compte[] tab = new Compte[clients.length * 2];
		int i = 0;
		for(Client cli : clients) {
			tab[i] = new CompteCourant(cli.getName(), i);
			tab[i + 1] = new CompteEpargne(cli.getName(), i+1);
			i+=2;
		}
		return tab;
	}
	public static void showCompte(Compte[] tab) {
		for(Compte cli : tab) {
			System.out.println(cli.toString());
		}
	}
	public static Hashtable getHashtable(Compte[] comptes){
		Hashtable <String, Compte> tab = new Hashtable<String, Compte>();
		Integer i = 0;
		for(Compte compte : comptes) {
			tab.put(i.toString(), compte);
			i++;
		}
		return tab;
	}
	public static void showHashtable(Hashtable tab) {
		System.out.println(Collections.singletonList(tab));
	}

}
