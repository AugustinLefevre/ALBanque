
package com.defaul;
import java.util.Date;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.Reader;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import com.composants.*;



import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


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
		// 2 GESTION DES COMPTES
		readJson();
		for(Flux f : flux) {
			System.out.println(f);
		}
		readXML();
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
		if(fluxType.equals("virement")) {
			tab[i] = new Virement(comment, id - 1, mount, idClient, true, date, compteDebit);
		} else if(fluxType.equals("debit")) {
			tab[i] = new Debit(comment, idClient - 1, mount, idClient, true, date);
		}
		else if(fluxType.equals("credit")) {
			tab[i] = new Credit(comment, idClient - 1, mount, idClient, true, date);
		}
		return tab;
	}
	public static void MAJComptes(Flux[] flux, Hashtable tab) {
		for(Flux flu : flux) {
			Integer i = flu.getID();
			Compte j = flu.getNBCompteEmeteur();
			Compte cpt = (Compte)tab.get(i.toString());
			cpt.setSolde(flu);
		}
	}
	public static Path getPath() {
        Path test = Paths.get("src/jsonData.json");
        System.out.println(test.toAbsolutePath());
        return test;
	}
	public static void readJson() {
		JSONParser parser = new JSONParser();
		String path = getPath().toString();
		try (Reader reader = new FileReader(path)) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			for (Integer i = 1; i <= jsonObject.size(); i++) {
				String elName = "el" + i.toString();
				JSONObject data = (JSONObject) jsonObject.get(elName);
				String fluxType =  (String) data.get("fluxType");
				String comm =  (String) data.get("commentaire");
				double mount =  (double) data.get("montant");
				Long id = (Long) data.get("identifiant");
				flux = addFluxInTab(fluxType, comm, mount, id.intValue(), flux, data.get("compteDebit"));
		}
		
		
	} catch (Exception e) {
        e.printStackTrace();
		}
	}
	public static void readXML() {
		try {
	         File inputFile = new File("src/DataFile.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("compte");
	         System.out.println("----------------------------");
	         Compte compte;
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               
	               String name = eElement.getElementsByTagName("nom").item(0).getTextContent();
	               String id = eElement.getElementsByTagName("numeroDeCompte").item(0).getTextContent();
	               if(eElement.getAttribute("type").equals("1"))
	            	   compte = new CompteCourant(name, Integer.parseInt(id));
	               else
	            	   compte = new CompteEpargne(name, Integer.parseInt(id));
	               compte.setLibelle(eElement.getElementsByTagName("libelle").item(0).getTextContent());
	               compte.setSolde(Double.parseDouble(eElement.getElementsByTagName("solde").item(0).getTextContent()));
	               comptCollection = updateComptArray(comptCollection, compte );

	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public static Compte[] updateComptArray(Compte[] comptes, Compte compte ) {
		
		Compte[] cpt = new Compte[comptes.length + 1];
		int i = 0;
		for(Compte c : comptes) {
			cpt[i] = c;
			i++;
		}
		cpt[i] = compte;
		return cpt;
	}
	  
}
