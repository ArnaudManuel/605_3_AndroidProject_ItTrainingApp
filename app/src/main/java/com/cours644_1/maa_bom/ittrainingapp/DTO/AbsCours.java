package com.cours644_1.maa_bom.ittrainingapp.DTO;

/**
 * Created by arnaud on 29.10.2015.
 */
public abstract class AbsCours {
    protected int id;
    protected String titre;
    protected  String descriptif;


    public int getId(){
        return id;
    }
    public String getTitre(){return titre;}
    public String getDescriptif(){return descriptif;}
}
