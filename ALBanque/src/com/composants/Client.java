//1.1.1 Création de la classe client
package com.composants;

public class Client {
	private String nom;
	private String prenom;
	private int numeroDeClient;
	private static int nbDeClient = 0;
	
	public Client(String cNom, String cPrenom) {
		this.nom = cNom;
		this.prenom = cPrenom;
		nbDeClient++;
		this.numeroDeClient = nbDeClient;
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
		this.nom = sName;
	}
	public void setLastName(String sPrenom) {
		this.prenom = sPrenom;
	}
	public void setClientID(int sClientID) {
		// Check if the id was not already used
		this.numeroDeClient = sClientID;
	}
	public String toString() {
		String str = this.nom + " " + this.prenom + " " + this.numeroDeClient;
		return str;
	}
}
