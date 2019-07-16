
package com.defaul;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import com.composants.*;

public class Main {

	private static Client[] collection = generateClient(2);
	private static Compte[] comptCollection = generateCompte(collection);
	private static Hashtable hashtable = getHashtable(comptCollection);
	private static Flux[] flux = new Flux[0];
	
	public static void main(String[] args) {
		//1.1.1 Création de la classe client
		showClient(collection);
		//1.2.3 Création du tableau des comptes
		showCompte(comptCollection);
		//1.3.1 Adaptation du tableau des comptes
		showHashtable(hashtable);
		//1.3.4 Création du tableau des flux
		flux = addFluxInTab("debit", "debittest", 50, 1,flux);
		for(Compte tab : comptCollection) {
			if(tab.getClass().getName() == "com.composants.CompteCourant")
			 flux = addFluxInTab("credit", "blabla", 100.50, tab.getNumeroDeCompte(), flux);
		}
		for(Compte tab : comptCollection) {
			if(tab.getClass().getName() == "com.composants.CompteEpargne")
			 flux = addFluxInTab("credit", "blabla", 1500, tab.getNumeroDeCompte(), flux);
		}
		flux = addFluxInTab("virement", "debittest", 50, 2,flux, hashtable.get("0"));
		
		
		// 1.3.5 Mise à jour des comptes
		MAJComptes(flux, hashtable);
		
		showCompte(comptCollection);
		
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
			tab[i] = new CompteCourant(cli.getName(), cli.getClientID());
			tab[i + 1] = new CompteEpargne(cli.getName(), cli.getClientID());
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
	public static Flux[] addFluxInTab(String fluxType,String comment, double mount, int idClient, Flux[] flux) {
		Flux[] tab = new Flux[flux.length + 1];
		int i = 0;
		for(Flux flu : flux) {
			tab[i] = flu;
			i++;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int id = idClient;
		if(fluxType == "debit")
			tab[i] = new Debit(comment, idClient - 1, mount, idClient, true, date);
		else if(fluxType == "credit")
			tab[i] = new Credit(comment, idClient - 1, mount, idClient, true, date);
		return tab;
	}
	public static Flux[] addFluxInTab(String fluxType,String comment, double mount, int idClient, Flux[] flux, Object compteDebit) {
		Flux[] tab = new Flux[flux.length + 1];
		int i = 0;
		for(Flux flu : flux) {
			tab[i] = flu;
			i++;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int id = idClient;
		if(fluxType == "virement") {
			tab[i] = new Virement(comment, id - 1, mount, idClient, true, date, compteDebit);
		}
		return tab;
	}
	public static void MAJComptes(Flux[] flux, Hashtable tab) {
		for(Flux flu : flux) {
			System.out.println( flu);
			System.out.println( flu.getID());
			Integer i = flu.getID();
			Compte j = flu.getNBCompteEmeteur();
			
			Compte cpt = (Compte)tab.get(i.toString());
			cpt.setSolde(flu);
		}
	}
}
