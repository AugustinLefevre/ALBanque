package com.composants;

import java.util.Date;

public class Virement extends Flux {

	public int NumeroDeCompteEmetteurDuVirement;
	
	public Virement(String comment, int id, double mount, int idClient, boolean b, Date date, int compteDebit) {
		// TODO Auto-generated constructor stub
		super(comment, idClient, mount, idClient, b, date);
		this.NumeroDeCompteEmetteurDuVirement = compteDebit;
	}
}
