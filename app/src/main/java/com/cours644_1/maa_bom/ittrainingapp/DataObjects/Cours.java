package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Cours {
    protected CoursData data;

    Cours(CoursData data){
        this.data= data;
    }

    public int getId(){
        return data.id;
    }
    public String getName(){
        return data.name;
    }
    public String getDescription(){
        return data.description;
    }
    public CoursModificator getModificator() {
        return new CoursModificator(data.getCopie());
    }

}
