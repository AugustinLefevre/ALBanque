package com.composants;
import java.sql.Date;

public abstract class Flux {
	private String commentaire;
	private int identifiant;
	private double montant;
	private int numeroDeCompteCible;
	boolean effectue;
	java.util.Date dateDuFlux;
	
	public Flux(){
		System.out.println("need bla arguments(commentaire, identifiant, montant, numeroDeCompteCible, done, dateDuFlux)");
	}
	public Flux(String comment,int id,double cost,int IDCompteCible,boolean done,java.util.Date date){
		this.commentaire = comment;
		this.identifiant = id;
		this.montant = cost;
		this.numeroDeCompteCible = IDCompteCible;
		this.effectue = done;
		this.dateDuFlux = date;
		System.out.println("is ok");
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
		return (Date) this.dateDuFlux;
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
	public String toString() {
		String str = new String("comment : " + this.commentaire + " id : " + this.identifiant + " mount : " + this.montant + " compte cible : " + this.numeroDeCompteCible + " done : " + this.effectue + " date : " + this.dateDuFlux);
		return str;
	}
}
