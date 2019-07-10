package com.composants;
import java.sql.Date;

public abstract class Flux {
	private String commentaire;
	private int identifiant;
	private double montant;
	private int numeroDeCompteCible;
	boolean effectue;
	Date dateDuFlux;
	
	public Flux(){
		System.out.println("need arguments(commentaire, identifiant, montant, numeroDeCompteCible, done, dateDuFlux)");
	}
	public Flux(String comment,int id,double cost,int IDCompteCible,boolean done,Date dateDuFlux){
		this.commentaire = comment;
		this.identifiant = id;
		this.montant = cost;
		this.numeroDeCompteCible = IDCompteCible;
		this.effectue = done;
		this.dateDuFlux = dateDuFlux;
	}
	public String getComment() {
		return this.commentaire;
	}
	public int getID() {
		return this.identifiant;
	}
	public double getCost() {
		return this.montant;
	}
	public int getIDComptCible() {
		return this.numeroDeCompteCible;
	}
	public boolean getIsDone() {
		return this.effectue;
	}
	public Date getDate() {
		return this.dateDuFlux;
	}
	public void setComment(String str) {
		this.commentaire = str;
	}
	public void setID(int i) {
		this.identifiant = i;
	}
	public void setCost(Double d) {
		this.montant = d;
	}
	public void setIDCompteCible(int i) {
		this.numeroDeCompteCible = i;
	}
	public void setIsDone(boolean b) {
		this.effectue = b;
	}
	public void setDate(Date d) {
		this.dateDuFlux = d;
	}
}
