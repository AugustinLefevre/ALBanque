//1.2.1 Création de la classe Compte
package com.composants;

public abstract class Compte {
	protected String libelle;
	protected double solde;
	protected int numeroDeCompte;
	protected Client cli;
	
	public Compte(String lib, int CompteID) {
		this.libelle = lib;
		this.numeroDeCompte = CompteID;
		
	}
	public String getLibelle() {
		return this.libelle;
	}
	public double getSolde() {
		return this.solde;
	}
	public int getNumeroDeCompte() {
		return this.numeroDeCompte;
	}
	public Client getClient() {
		return this.cli;
	}
	public void setLibelle(String lib) {
		this.libelle = lib;
	}
	public void setSolde(Flux flu) {
		this.solde = solde;
	}
	public void setNumeroDeCompte(int CompteID) {
		this.numeroDeCompte = CompteID;
	}
	public void setClient(Client cli) {
		this.cli = cli;
	}
	public String toString() {
		String str = new String(this.libelle + " " + this.numeroDeCompte + " " + this.solde);
		return str;
	}
}
