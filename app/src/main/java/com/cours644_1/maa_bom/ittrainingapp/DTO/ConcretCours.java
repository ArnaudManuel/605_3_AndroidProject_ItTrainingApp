package com.cours644_1.maa_bom.ittrainingapp.DTO;

/**
 * Created by arnaud on 29.10.2015.
 */
public class ConcretCours extends AbsCours {
    public ConcretCours(int id, String titre, String descriptif){
        super();
        this.id=id;
        this.titre=titre;
        this.descriptif=descriptif;
    }
}
