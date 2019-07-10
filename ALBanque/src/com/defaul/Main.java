// 1.1.2 Création d’un classe main pour les tests
package com.defaul;
import com.composants.*;

public class Main {

	private static Client[] collection = generateClient(2);

	public static void main(String[] args) {
		
		showClient(collection);
		// showClient(generateClient(2)); ??
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

}
