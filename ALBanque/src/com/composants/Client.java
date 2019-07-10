//1.1.1 Création de la classe client
package com.composants;

public class Client {
	private String nom;
	private String prenom;
	private int numeroDeClient;
	private static int nbDeClient = 0;
	
	public Client(String cNom, String cPrenom) {
		nom = cNom;
		prenom = cPrenom;
		nbDeClient++;
		numeroDeClient = nbDeClient;
	}
	public String getName() {
		return this.nom;
	}
	public String getLastName() {
		return this.prenom;
	}
	public int getClientID() {
		return this.numeroDeClient;
	}
	public static int getClientsNumber() {
		return nbDeClient;
	}
	public void setName(String sName) {
		nom = sName;
	}
	public void setLastName(String sPrenom) {
		prenom = sPrenom;
	}
	public void setClientID(int sClientID) {
		// Check if the id was not already used
		numeroDeClient = sClientID;
	}
	public String toString() {
		String str = new String(this.nom + " " + this.prenom + " " + numeroDeClient);
		return str;
	}
}
