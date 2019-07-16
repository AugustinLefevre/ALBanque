//1.2.1 Création de la classe Compte
package com.composants;

public abstract class Compte {
	protected String libelle;
	protected double solde;
	protected int numeroDeCompte;
	protected static int nbCompte = 0;

	protected Client cli;
	
	public Compte(String lib, int CompteID) {
		this.libelle = lib;
		nbCompte++;
		this.numeroDeCompte = nbCompte;
		
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
	public void setSolde(double i) {

	}
	public void setSolde(Flux flu) {
		if(flu.getClass().getName() == "com.composants.Credit") {
			this.solde += flu.getCost();
		}else if (flu.getClass().getName() == "com.composants.Debit") {
			this.solde -= flu.getCost();
		}else {

			if(this.numeroDeCompte == flu.getIDComptCible()) {
				this.solde += flu.getCost();
				flu.getNBCompteEmeteur().setSolde(flu.getCost() * -1);		
			}
//			else if(flu.getNBCompteEmeteur().getNumeroDeCompte() == this.numeroDeCompte) {
//				System.out.println(flu.getNBCompteEmeteur().getNumeroDeCompte());
//
//			}
		}
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
