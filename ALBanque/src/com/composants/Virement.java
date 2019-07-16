package com.composants;

import java.util.Date;

public class Virement extends Flux {

	public Compte NumeroDeCompteEmetteurDuVirement;
	
	public Virement(String comment, int id, double mount, int idClient, boolean b, Date date, Object compteDebit) {
		super(comment, id, mount, idClient, b, date);
		this.NumeroDeCompteEmetteurDuVirement = (Compte) compteDebit;
	}
	public Compte getNBCompteEmeteur() {
		return this.NumeroDeCompteEmetteurDuVirement;
	}
}
