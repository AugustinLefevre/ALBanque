// 1.2.2 Création des classes CompteEpargne
package com.composants;

public class CompteEpargne extends Compte {
	//public String libelle;
	public CompteEpargne(String libelle, int CompteID) {
		super(libelle, CompteID);
	//	this.libelle = libelle;
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
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public void setNumeroDeCompte(int CompteID) {
		this.numeroDeCompte = CompteID;
	}
	public void setClient(Client cli) {
		this.cli = cli;
	}
	public String toString() {
		String str = this.libelle + " compte epargne " + this.numeroDeCompte + " " + this.solde ;
		return str;
	}

}

