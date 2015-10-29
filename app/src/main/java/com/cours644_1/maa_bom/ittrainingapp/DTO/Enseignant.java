package com.cours644_1.maa_bom.ittrainingapp.DTO;

/**
 * Created by arnaud on 29.10.2015.
 */
public class Enseignant extends AbsEnseignant {
    public Enseignant(int id, String nom, String prenom, String mail, String presentation){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.presentation=presentation;
    }
}
