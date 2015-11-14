package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class CoursModificator extends Cours {

    CoursModificator(CoursData data) {
        super(data);
    }

    public void setName(String name){
        data.name=name;
    }
    public void setDescription(String description){
        data.description=description;
    }
    public boolean save(){
        //TODO sauvegarde.
        return false;
    }
}
