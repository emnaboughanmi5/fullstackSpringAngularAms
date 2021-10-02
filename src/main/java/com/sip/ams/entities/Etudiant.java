package com.sip.ams.entities;

public class Etudiant {
    private int id;
    private String nom;
    private String email;

    public Etudiant() {

    }

    public Etudiant(int id, String nom, String email) {
	super();
	this.id = id;
	this.nom = nom;
	this.email = email;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
