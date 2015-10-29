package com.cours644_1.maa_bom.ittrainingapp.DTO;

/**
 * Created by arnaud on 29.10.2015.
 */
public class CoursUnderConstruct extends AbsCours {
    public CoursUnderConstruct(){
        this.id=-1;
    }
    public void setTitre(String titre){
        this.titre=titre;
    }
    public void setDecriptif(String desciptif){
        this.descriptif=desciptif;
    }
    public ConcretCours validate(){
        //TODO sauvegarde
        return  null;
    }

}
