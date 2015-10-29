package com.cours644_1.maa_bom.ittrainingapp.DTO;

/**
 * Created by arnaud on 29.10.2015.
 */
public abstract class Personne {
    protected int id;
    protected String titre;
    protected String nom;
    protected String prenom;
    protected String mail;

    public int getId(){return id;}
    public String getTitre(){return titre;}
    public String getNom(){return nom;}
    public String getPrenom(){return prenom;}
    public String getMail(){return mail;}


}
