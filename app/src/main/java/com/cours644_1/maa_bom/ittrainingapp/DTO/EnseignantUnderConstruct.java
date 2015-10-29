package com.cours644_1.maa_bom.ittrainingapp.DTO;

/**
 * Created by arnaud on 29.10.2015.
 */
public class EnseignantUnderConstruct extends AbsEnseignant {
    public EnseignantUnderConstruct(){
        this.id=-1;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setPresenetation(String presentation){
        this.presentation = presentation;
    }
    public Enseignant validate(){
        //TODO sauvegarde
        return null;
    }
}
